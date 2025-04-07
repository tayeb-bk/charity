package com.charity2.dto;

public class AvisRequest {
    private String content;
    private String categories;
    private String tags;

    // Constructors, Getters, and Setters
    public AvisRequest() {}

    public AvisRequest(String content, String categories, String tags) {
        this.content = content;
        this.categories = categories;
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}