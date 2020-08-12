package com.example.TelegramBot.DTO;

public class UserDTO {
    private String status;

    public UserDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

