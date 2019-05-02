package com.robocat.shopify.jsdintegration.amp.service;

import com.atlassian.connect.spring.AtlassianHostRestClients;
import com.robocat.shopify.jsdintegration.amp.dto.JiraIssueComment;
import com.robocat.shopify.jsdintegration.amp.dto.JiraIssueCommentBody;
import com.robocat.shopify.jsdintegration.amp.dto.JiraIssueCommentsResult;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AmpService {

    private final AtlassianHostRestClients atlassianHostRestClients;

    public AmpService(AtlassianHostRestClients atlassianHostRestClients) {
        this.atlassianHostRestClients = atlassianHostRestClients;
    }

    public JiraIssueCommentsResult postCommentToJiraIssue(String issueId, String comment) {
        atlassianHostRestClients
                .authenticatedAsAddon()
                .postForEntity("https://jsdshopify.atlassian.net/rest/api/3/issue/{issueIdOrKey}/comment",
                        new JiraIssueComment(
                                new JiraIssueCommentBody(
                                        Collections.singletonList(new JiraIssueCommentBody.BodyContent(
                                                Collections.singletonList(
                                                        new JiraIssueCommentBody.Content(comment)
                                                )
                                        ))
                                ),
                                true
                        ),
                        Void.class,
                        issueId
                );
        return getCommentsFromJira(issueId);
    }

    public JiraIssueCommentsResult getCommentsFromJira(String issueIdOrKey) {
        return atlassianHostRestClients
                .authenticatedAsAddon()
                .getForEntity(
                        "https://jsdshopify.atlassian.net/rest/api/3/issue/{issueIdOrKey}/comment",
                        JiraIssueCommentsResult.class,
                        issueIdOrKey
                ).getBody();
    }

}
