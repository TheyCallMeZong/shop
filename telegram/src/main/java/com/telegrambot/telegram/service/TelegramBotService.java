package com.telegrambot.telegram.service;

import com.telegrambot.telegram.config.BotConfig;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotService extends TelegramLongPollingBot {
    private final BotConfig config;

    @Autowired
    public TelegramBotService(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        if (message.hasText()){
            String text = message.getText();
            switch (text){
                case "/start":
                    String answer = "Hi, " + update.getMessage().getChat().getUserName() + ", nice to meet you!";
                    sendMessage(answer, message.getChatId());
                    break;
            }
        }
    }

    private void sendMessage(String message, long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);
        try{
            execute(sendMessage);
        } catch (TelegramApiException ex){
            System.out.println("gde to chto to ne polychilos'");
        }
    }
}
