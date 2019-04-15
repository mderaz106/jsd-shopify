package com.robocat.shopify.jsdintegration.shopify.controller;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.atlassian.connect.spring.IgnoreJwt;
import com.robocat.shopify.jsdintegration.shopify.api.ShopifyApiService;
import com.robocat.shopify.jsdintegration.shopify.dto.ShopifyCustomer;
import com.robocat.shopify.jsdintegration.shopify.exception.CustomerNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/jira/connect/shopify/customer")
public class JiraServiceDeskShopifyCustomerController {
    private final ShopifyApiService shopifyApiService;

    public JiraServiceDeskShopifyCustomerController(ShopifyApiService shopifyApiService) {
        this.shopifyApiService = shopifyApiService;
    }

    @GetMapping
    @IgnoreJwt
    public ShopifyCustomer getShopifyCustomerInformation(@RequestParam String email) throws IOException {
        return shopifyApiService.getShopifyCustomerWithOrders(email).orElseThrow(CustomerNotFoundException::new);
    }


}
