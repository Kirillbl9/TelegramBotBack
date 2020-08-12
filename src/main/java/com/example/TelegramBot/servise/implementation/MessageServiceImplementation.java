package com.example.TelegramBot.servise.implementation;

import com.example.TelegramBot.DTO.MessageDTO;
import com.example.TelegramBot.model.Message;
import com.example.TelegramBot.repo.MessageRepository;
import com.example.TelegramBot.servise.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImplementation.class);


    final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImplementation(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDTO> findAllByAuthorId(Long id) {
        if(messageRepository.findMessageByAuthorId(id) == null){
            logger.error("Call find all users by id method. User with id " + id + "not found");
        }
        logger.debug("Call find all users by id method");
        List<Message> listMessage = messageRepository.findAllByAuthorId(id);
        //listDTOMessage
        return ofList(listMessage);
    }

    @Override
    public List<MessageDTO> findAll() {
        logger.debug("Call find all users method");
        List<Message> listMessage = messageRepository.findAll();
        //listDTOMessage
        return ofList(listMessage);
    }

    private List<MessageDTO> ofList(List<Message> messageList){
        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message x : messageList) {
            messageDTOs.add(new MessageDTO(x.getAuthorId().toString(), x.getText()));
        }
        return messageDTOs;
    }
}
