package com.example.TelegramBot.controller;

import com.example.TelegramBot.DTO.MessDTO;
import com.example.TelegramBot.model.Mess;
import com.example.TelegramBot.repo.MessageRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.example.TelegramBot.servise.MessageSender.sendToTelegram;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping(value = "/message")
    public List<Mess> getAllMessages() {
        return messageRepository.findAll();
    }

    @PostMapping(value = "/message/post")
    public void postMessage(@RequestBody MessDTO message) {
        sendToTelegram(message.getAuthorId(), message.getText());
    }
    @GetMapping(value = "/message/get/{authorId}")
    public List<Mess> getCourseById(@PathVariable("authorId") Long Id) {
        return messageRepository.findAllByAuthorId(Id);
    }
}
