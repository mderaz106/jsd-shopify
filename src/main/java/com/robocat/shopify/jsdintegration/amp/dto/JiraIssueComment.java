package com.robocat.shopify.jsdintegration.amp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JiraIssueComment {
    private JiraIssueCommentBody body;
    private boolean jsdPublic;
}
