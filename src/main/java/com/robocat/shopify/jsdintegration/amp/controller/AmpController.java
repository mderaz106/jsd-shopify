package com.robocat.shopify.jsdintegration.amp.controller;

import com.atlassian.connect.spring.IgnoreJwt;
import com.robocat.shopify.jsdintegration.amp.dto.JiraIssueCommentsResult;
import com.robocat.shopify.jsdintegration.amp.service.AmpService;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/jira/issue/comment")
public class AmpController {

    private final AmpService ampService;

    public AmpController(AmpService ampService) {
        this.ampService = ampService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @IgnoreJwt
    @CrossOrigin(exposedHeaders = {"AMP-Access-Control-Allow-Source-Origin"})
    public JiraIssueCommentsResult addCommentToJiraIssue(@RequestBody MultiValueMap<String, String> formData, HttpServletResponse response) {
        response.addHeader("AMP-Access-Control-Allow-Source-Origin", "amp@gmail.dev");
        return ampService.postCommentToJiraIssue(formData.getFirst("issueId"), formData.getFirst("comment"));
    }

    @GetMapping
    @IgnoreJwt
    public JiraIssueCommentsResult getAllJiraIssueComments(@RequestParam String issueKeyOrId) {
        return ampService.getCommentsFromJira(issueKeyOrId);
    }
}
