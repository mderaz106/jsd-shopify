package com.robocat.shopify.jsdintegration.shopify.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "shopify")
public class ShopifyConfig {
    private String baseUrl;
    private String apiKey;
    private String apiPassword;
}
