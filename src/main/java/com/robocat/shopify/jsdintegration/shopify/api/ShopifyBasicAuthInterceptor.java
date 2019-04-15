package com.robocat.shopify.jsdintegration.shopify.api;


import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ShopifyBasicAuthInterceptor implements Interceptor {

    private final String apiKey;
    private final String apiPassword;

    ShopifyBasicAuthInterceptor(String apiKey, String apiPassword) {
        this.apiKey = apiKey;
        this.apiPassword = apiPassword;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                Credentials.basic(apiKey, apiPassword));

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
