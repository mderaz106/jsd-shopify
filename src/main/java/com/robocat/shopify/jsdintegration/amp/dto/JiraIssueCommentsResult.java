package com.robocat.shopify.jsdintegration.amp.dto;

import lombok.Data;

import java.util.List;

@Data
public class JiraIssueCommentsResult {
    private List<JiraIssueComment> comments;
}
