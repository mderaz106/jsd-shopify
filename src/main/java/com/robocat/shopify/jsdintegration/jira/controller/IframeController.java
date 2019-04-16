package com.robocat.shopify.jsdintegration.jira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jira/connect")
public class IframeController {

    @GetMapping("/iframe")
    public String getIndex() {
        return "iframe";
    }

}
