package com.example.TelegramBot.repo;

import com.example.TelegramBot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByAuthorId(Long id);
    List<Message> findAll();
    Message findMessageByAuthorId(Long id);
}
