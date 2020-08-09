package com.example.TelegramBot.repo;

import com.example.TelegramBot.model.Mess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Mess, Long> {
    List<Mess> findAllByAuthorId(Long id);
}
