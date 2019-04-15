package com.robocat.shopify.jsdintegration.jira.service;

import com.atlassian.connect.spring.AtlassianHostRestClients;
import com.robocat.shopify.jsdintegration.jira.dto.JiraIssue;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JiraApiService {

    private final AtlassianHostRestClients atlassianHostRestClients;

    public JiraApiService(AtlassianHostRestClients atlassianHostRestClients) {

        this.atlassianHostRestClients = atlassianHostRestClients;
    }

    public String getJiraIssueReporter(Long issueId) {
        ResponseEntity<JiraIssue> responseEntity = atlassianHostRestClients.authenticatedAsAddon()
                .getForEntity("/rest/api/3/issue/{issueId}?fields=reporter",
                        JiraIssue.class,
                        issueId);
        return responseEntity.getBody().getReporter().getEmailAddress();
    }
}
