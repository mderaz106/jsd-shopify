package com.robocat.shopify.jsdintegration.shopify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopifyCustomersResponse {
    private List<ShopifyCustomer> customers;
}
