package com.robocat.shopify.jsdintegration.shopify.api;

import com.robocat.shopify.jsdintegration.shopify.dto.ShopifyCustomersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShopifyRetrofitCaller {

    @GET("customers/search.json")
    Call<ShopifyCustomersResponse> getCustomerInformation(@Query("query") String query);

    @GET("customers/{customerId}/orders.json")
    Call<ShopifyOrdersResponse> getCustomerOrders(@Path("customerId") long customerId, @Query("status") String status);
}
