package com.example.TelegramBot.controller;

import com.example.TelegramBot.DTO.MessageDTO;
import com.example.TelegramBot.bot.ChatBot;
import com.example.TelegramBot.servise.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MessageController {
    private final ChatBot chatBot;
    private final MessageService messageService;

    @Autowired
    public MessageController(ChatBot chatBot, MessageService messageService) {
        this.chatBot = chatBot;
        this.messageService = messageService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/message")
    public List<MessageDTO> getAllMessages() {
        return messageService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/message/post")
    public void postMessage(@RequestBody MessageDTO message) {
        chatBot.sendMessage(message.getAuthorId(), message.getText());

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/message/get/{authorId}")
    public List<MessageDTO> getListMessagesById(@PathVariable("authorId") Long Id) {
        return messageService.findAllByAuthorId(Id);
    }
}
