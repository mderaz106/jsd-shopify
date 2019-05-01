package com.robocat.shopify.jsdintegration.amp.controller;

import com.atlassian.connect.spring.IgnoreJwt;
import com.robocat.shopify.jsdintegration.amp.dto.JiraIssueComment;
import com.robocat.shopify.jsdintegration.amp.service.AmpService;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jira/issue/comment")
public class AmpController {

    private final AmpService ampService;

    public AmpController(AmpService ampService) {
        this.ampService = ampService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @IgnoreJwt
    public List<JiraIssueComment> addCommentToJiraIssue(@RequestBody MultiValueMap<String, String> formData) {
        return ampService.postCommentToJiraIssue(formData.getFirst("issueId"), formData.getFirst("comment"));
    }

    @GetMapping
    @IgnoreJwt
    public List<JiraIssueComment> getAllJiraIssueComments(@RequestParam String issueKeyOrId) {
        return ampService.getCommentsFromJira(issueKeyOrId);
    }
}
