package com.robocat.shopify.jsdintegration.shopify.api;

import com.robocat.shopify.jsdintegration.shopify.dto.ShopifyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopifyOrdersResponse {
    private List<ShopifyOrder> orders;
}
