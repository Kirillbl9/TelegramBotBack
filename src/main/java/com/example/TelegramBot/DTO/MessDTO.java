package com.example.TelegramBot.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessDTO {
    @JsonProperty("authorId")
    private String authorId;
    @JsonProperty("text")
    private String text;

    public MessDTO() {
    }

    public MessDTO(String authorId, String text) {
        this.authorId = authorId;
        this.text = text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessDTO{" +
                "authorId=" + authorId +
                ", text='" + text + '\'' +
                '}';
    }
}
