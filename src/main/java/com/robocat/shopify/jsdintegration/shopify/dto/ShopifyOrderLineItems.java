package com.robocat.shopify.jsdintegration.shopify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopifyOrderLineItems {
    private long id;
    private String name;
    private String sku;
}

