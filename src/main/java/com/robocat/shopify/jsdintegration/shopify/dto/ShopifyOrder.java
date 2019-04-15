package com.robocat.shopify.jsdintegration.shopify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopifyOrder {

    private long id;

    private String token;

    @JsonProperty("total_price")
    private BigDecimal totalPrice;

    @JsonProperty("subtotal_price")
    private BigDecimal subtotalPrice;

    @JsonProperty("order_number")
    private long orderNumber;

    private String currency;

    @JsonProperty("financial_status")
    private String financialStatus;

    private String name;

    @JsonProperty("line_items")
    private List<ShopifyOrderLineItems> lineItems;
}
