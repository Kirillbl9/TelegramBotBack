package com.example.TelegramBot.bot;

import com.example.TelegramBot.model.Message;
import com.example.TelegramBot.repo.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(ChatBot.class);

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private MessageRepository messageRepository;

    @Autowired
    public ChatBot(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public ChatBot() {
    }

    @Override
    public void onUpdateReceived(Update update) {
        org.telegram.telegrambots.meta.api.objects.Message message = update.getMessage();
        Message mes = new Message(message.getChatId(), message.getText());
        messageRepository.save(mes);
        if (message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendMessage(message.getChatId(), "Привет!");
                    sendMessage(message.getChatId(), "список соманд - /commands\nвопрос админу - /admin");
                    break;
                case "/help":
                    sendMessage(message.getChatId(), "Чем могу помочь?");
                    break;
                case "/setting":
                    sendMessage(message.getChatId(), "Что будем настраивать");
                    break;
                case "/commands":
                    sendMessage(message.getChatId(), "/help\n/setting\n/admin\n");
                    break;
                case "/admin":
                    sendMessage(message.getChatId(), "Теперь задайте вопрос, в ближайшее время на него ответят");
                    break;
            }
        }
    }

    public void sendMessage(long chatId, String text){
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        try {
            execute(message);
            logger.debug("Successful message sending from author with id: " + chatId);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            logger.error("error sending message by author with id: " + chatId);
        } finally {
            onClosing();
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

