package com.robocat.shopify.jsdintegration.amp.controller;

import com.atlassian.connect.spring.IgnoreJwt;
import com.robocat.shopify.jsdintegration.amp.service.AmpService;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmpController {

    private final AmpService ampService;

    public AmpController(AmpService ampService) {
        this.ampService = ampService;
    }

    @PostMapping(value = "/jira/issue/comment",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @IgnoreJwt
    public void addCommentToJiraIssue(@RequestBody MultiValueMap<String, String> formData) {
        ampService.postCommentToJiraIssue(formData.getFirst("issueId"), formData.getFirst("comment"));
    }

}
