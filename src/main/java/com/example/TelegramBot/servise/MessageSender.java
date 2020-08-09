package com.example.TelegramBot.servise;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MessageSender {
    public static void sendToTelegram(String chatId, String text) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        String apiToken = "1336997208:AAFRFKWzBEhwgR7-GAZRdXagDx5c0w5HKlY";
        urlString = String.format(urlString, apiToken, chatId, text);
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
