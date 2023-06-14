
package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.nio.charset.StandardCharsets;
import java.util.*;

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

        Long ChatId=getChatId(update);
if(update.hasMessage()&&update.getMessage().getText().equals("/start")){
    SendMessage message= createMessage("Привіт");
    message.setChatId(ChatId);
    attachButtons(message, Map.of(
            "Слава Україні", "glory_for_ukraine"
    ));
    sendApiMethodAsync(message);
}
        if (update.hasCallbackQuery()){
    if (update.getCallbackQuery().getData().equals("glory_for_ukraine")){
        SendMessage message=createMessage("Героям слава");
        attachButtons(message, Map.of(

                "Слава Нації", "glory_for-nation"
        ));
        message.setChatId(ChatId);
        sendApiMethodAsync(message);
    } else if (update.getCallbackQuery().getData().equals("glory_for-nation")) {
        SendMessage message=createMessage("Смерть ворогам");
        message.setChatId(ChatId);
        sendApiMethodAsync(message);

    }
}

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
    public void attachButtons(SendMessage message, Map <String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String buttonName:buttons.keySet()) {
            String buttonValue=buttons.get(buttonName);
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonName);
            button.setCallbackData(buttonValue);
            keyboard.add(Arrays.asList(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }}
