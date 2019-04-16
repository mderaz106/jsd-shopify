package com.robocat.shopify.jsdintegration.jira.service;

import com.atlassian.connect.spring.AtlassianHostRestClients;
import com.robocat.shopify.jsdintegration.jira.dto.JiraIssue;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JiraApiService {

    private RestTemplate restTemplate;

    public JiraApiService(AtlassianHostRestClients atlassianHostRestClients) {

        restTemplate = atlassianHostRestClients.authenticatedAsAddon();
    }

    public String getJiraIssueReporter(Long issueId) {
        ResponseEntity<JiraIssue> responseEntity = restTemplate
                .getForEntity("/rest/api/3/issue/{issueId}?fields=*all",
                        JiraIssue.class,
                        issueId);
        return responseEntity.getBody().getFields().getReporter().getEmailAddress();
    }
}
