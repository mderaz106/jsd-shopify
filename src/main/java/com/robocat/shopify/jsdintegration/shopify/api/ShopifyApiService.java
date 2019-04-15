package com.robocat.shopify.jsdintegration.shopify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robocat.shopify.jsdintegration.shopify.config.ShopifyConfig;
import com.robocat.shopify.jsdintegration.shopify.dto.ShopifyCustomer;
import com.robocat.shopify.jsdintegration.shopify.dto.ShopifyOrder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ShopifyApiService {

    private static final String STATUS_ANY = "any";

    private final ShopifyRetrofitCaller shopifyRetrofitCaller;

    public ShopifyApiService(ShopifyConfig shopifyConfig,
                             ObjectMapper objectMapper) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(shopifyConfig.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory
                        .create(objectMapper))
                .client(
                        new OkHttpClient()
                                .newBuilder()
                                .addInterceptor(
                                        new ShopifyBasicAuthInterceptor(
                                                shopifyConfig.getApiKey(),
                                                shopifyConfig.getApiPassword())
                                )
                                .build()
                )
                .build();
        shopifyRetrofitCaller = retrofit.create(ShopifyRetrofitCaller.class);
    }

    public Optional<ShopifyCustomer> getShopifyCustomerWithOrders(String email) throws IOException {
        List<ShopifyCustomer> customers = Objects.requireNonNull(shopifyRetrofitCaller.getCustomerInformation(buildEmailQuery(email))
                .execute()
                .body())
                .getCustomers();
        if (customers != null && customers.size() > 0) {
            ShopifyCustomer shopifyCustomer = customers.get(0);
            long customerId = shopifyCustomer.getId();
            List<ShopifyOrder> orders = Objects.requireNonNull(shopifyRetrofitCaller.getCustomerOrders(customerId, STATUS_ANY)
                    .execute()
                    .body())
                    .getOrders();
            shopifyCustomer.setOrders(orders);
            return Optional.of(shopifyCustomer);
        }
        return Optional.empty();
    }

    private String buildEmailQuery(String email) {
        return String.format("email:%s", email);
    }
}
