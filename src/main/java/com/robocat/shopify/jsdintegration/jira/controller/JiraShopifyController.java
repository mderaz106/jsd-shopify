package com.robocat.shopify.jsdintegration.jira.controller;

import com.robocat.shopify.jsdintegration.jira.service.JiraApiService;
import com.robocat.shopify.jsdintegration.shopify.api.ShopifyApiService;
import com.robocat.shopify.jsdintegration.shopify.dto.ShopifyCustomer;
import com.robocat.shopify.jsdintegration.shopify.exception.CustomerNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/jira/connect")
public class JiraShopifyController {

    private final JiraApiService jiraApiService;
    private final ShopifyApiService shopifyApiService;

    public JiraShopifyController(JiraApiService jiraApiService,
                                 ShopifyApiService shopifyApiService) {
        this.jiraApiService = jiraApiService;
        this.shopifyApiService = shopifyApiService;
    }

    @GetMapping("/reporter/shopify-details")
    public ShopifyCustomer getJsdShopifyCustomerDetails(@RequestParam Long issueId) throws IOException {
        String reporterEmail = jiraApiService.getJiraIssueReporter(issueId);
        return shopifyApiService.getShopifyCustomerWithOrders(reporterEmail)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
    }

}
