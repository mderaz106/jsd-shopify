package com.robocat.shopify.jsdintegration.amp.dto;

import lombok.Data;

import java.util.List;

@Data
public class JiraIssueCommentBody {
    private int version = 1;
    private String type = "doc";
    private BodyContent content;

    public JiraIssueCommentBody(BodyContent content) {
        this.content = content;
    }

    @Data
    public static class BodyContent {
        private String type = "paragraph";
        private List<Content> content;

        public BodyContent(List<Content> content) {
            this.content = content;
        }
    }

    @Data
    public static class Content {
        private String type = "text";
        private String text;

        public Content(String text) {
            this.text = text;
        }
    }
}
