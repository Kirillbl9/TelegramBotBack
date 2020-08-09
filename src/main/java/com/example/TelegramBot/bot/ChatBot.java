package com.example.TelegramBot.bot;

import com.example.TelegramBot.model.Mess;
import com.example.TelegramBot.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private final MessageRepository messageRepository;

    public ChatBot(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Mess mes = new Mess(message.getChatId(), message.getText());
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
        } catch (TelegramApiException e) {
            e.printStackTrace();
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

