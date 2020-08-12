package com.example.TelegramBot.servise;

import com.example.TelegramBot.DTO.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> findAllByAuthorId(Long id);
    List<MessageDTO> findAll();
}
