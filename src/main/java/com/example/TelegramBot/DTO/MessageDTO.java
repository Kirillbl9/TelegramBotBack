package com.example.TelegramBot.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageDTO {
    @JsonProperty("authorId")
    private Long authorId;
    @JsonProperty("text")
    private String text;

    public MessageDTO() {
    }

    public MessageDTO(Long authorId, String text) {
        this.authorId = authorId;
        this.text = text;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
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
