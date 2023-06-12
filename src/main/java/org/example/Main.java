
package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;

public class Main extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi api=new TelegramBotsApi(DefaultBotSession.class);
        System.out.printf("Hello and welcome!");
api.registerBot(new Main());
    }

    @Override
    public String getBotUsername() {
        return "MyPetProjectJavaBot";
    }

    @Override
    public String getBotToken() {
        return "5915831284:AAHktUmJ-Ue7crCmuuX5IgmVgKygHlxhOgQ";
    }

    @Override
    public void onUpdateReceived(Update update) {
       // update.getMessage().getFrom().getId();
        Long ChatId=getChatId(update);
       // SendMessage message =new SendMessage();
       // message.setText("Hello!!");
        //message.setChatId(ChatId);
       // sendApiMethodAsync(message);
       // message.setChatId();
        SendMessage snd=createMessage("*Hello* "+ update.getMessage().getFrom().getFirstName() );
        snd.setChatId(ChatId);
        sendApiMethodAsync(snd);
    }
    public Long getChatId(Update update){
        if (update.hasMessage())
            return update.getMessage().getFrom().getId();
        if (update.hasCallbackQuery())
            return (update.getCallbackQuery().getFrom().getId());
        return null;
    }


    public SendMessage createMessage(String text){
        SendMessage message =new SendMessage();
        message.setText(text);
        message.setParseMode("markdown");
        return message;
    }
}