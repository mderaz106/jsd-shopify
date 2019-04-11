package com.robocat.shopify.jsdintegration.jira;

import com.atlassian.connect.spring.IgnoreJwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jira/connect")
public class IframeController {

    @GetMapping("/iframe")
    @IgnoreJwt
    public String getIndex() {
        return "iframe";
    }

}
