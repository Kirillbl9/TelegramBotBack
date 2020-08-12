package com.example.TelegramBot.controller;

import com.example.TelegramBot.DTO.MessageDTO;
import com.example.TelegramBot.bot.ChatBot;
import com.example.TelegramBot.servise.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class MessageController {
    private final ChatBot chatBot;
    private final MessageService messageService;

    @Autowired
    public MessageController(ChatBot chatBot, MessageService messageService) {
        this.chatBot = chatBot;
        this.messageService = messageService;
    }


    @GetMapping(value = "/message")
    public List<MessageDTO> getAllMessages() {
        return messageService.findAll();
    }

    @PostMapping(value = "/message/post")
    public void postMessage(@RequestBody MessageDTO message) {
        chatBot.sendMessage(Long.parseLong(message.getAuthorId()), message.getText());

    }

    @GetMapping(value = "/message/get/{authorId}")
    public List<MessageDTO> getListMessagesById(@PathVariable("authorId") Long Id) {
        return messageService.findAllByAuthorId(Id);
    }
}
