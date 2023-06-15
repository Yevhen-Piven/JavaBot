
package org.example;

import javafx.animation.Animation;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main extends TelegramLongPollingBot {
private Map<Long,Integer> levels=new HashMap<>() ;
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

        Long chatId=getChatId(update);
if(update.hasMessage()&&update.getMessage().getText().equals("/start")){

    sendImage("level-1", chatId);
    SendMessage message = createMessage("Ґа-ґа-ґа!\n" +
            "Вітаємо у боті біолабораторії «Батько наш Бандера».\n" +
            "\n" +
            "Ти отримуєш гусака №71\n" +
            "\n" +
            "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської худоби до рівня біологічної зброї, здатної нищити ворога. \n" +
            "\n" +
            "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\n" +
            "✔\uFE0Fвиконувати завдання\n" +
            "✔\uFE0Fпереходити на наступні рівні\n" +
            "✔\uFE0Fзаробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\n" +
            "\n" +
            "*Гусак звичайний.* Стартовий рівень.\n" +
            "Бонус: 5 монет.\n" +
            "Обери завдання, щоб перейти на наступний рівень");
    message.setChatId(chatId);
    attachButtons(message,Map.of(
            "Сплести маскувальну сітку (+15 монет)","level-1_task",
            "Зібрати кошти патріотичними піснями (+15 монет)","level-1_task",
            "Вступити в Міністерство Мемів України (+15 монет)","level-1_task"
    ));
    sendApiMethodAsync(message);}
    //SendMessage message= createMessage("Привіт");
    // message.setChatId(chatId);
    //attachButtons(message, Map.of(
    //       "Слава Україні", "glory_for_ukraine"
    //));
    // sendApiMethodAsync(message);
//}
     if (update.hasCallbackQuery()){
     if (update.getCallbackQuery().getData().equals("level-1_task")&&getLevel(chatId)==1) {
         setLevel(chatId,2);
         sendImage("level-2",chatId);
         SendMessage message=createMessage("*Вітаємо на другому рівні! Твій гусак - біогусак.*\n" +
                 "Баланс: 20 монет. \n" +
                 "Обери завдання, щоб перейти на наступний рівень\n");
         message.setChatId(chatId);
         attachButtons(message,Map.of(
                 "Зібрати комарів для нової біологічної зброї (+15 монет)","level-2_task",
                 "Пройти курс молодого бійця (+15 монет) ","level-2_task",
                 "Задонатити на ЗСУ (+15 монет) ","level-2_task",
                 "Збити дрона банкою огірків (+15 монет) ","level-2_task",
                 "Зробити запаси коктейлів Молотова (+15 монет) ","level-2_task"
         ));
         sendApiMethodAsync(message);
         }}


        if (update.hasCallbackQuery()){
            if (update.getCallbackQuery().getData().equals("level-2_task")&&getLevel(chatId)==2) {
                setLevel(chatId,3);
                sendImage("level-3",chatId);
                SendMessage message=createMessage("*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\n" +
                        "Баланс: 35 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");
                message.setChatId(chatId);
                attachButtons(message,Map.of(
                        "Злітати на тестовий рейд по чотирьох позиціях (+15 монет) ","level-3_task",
                        "Відвезти гуманітарку на передок (+15 монет) ","level-3_task",
                        "Знайти зрадника та здати в СБУ (+15 монет) ","level-3_task",
                        "Навести арту на орків (+15 монет)","level-3_task",
                        "Притягнути танк трактором (+15 монет)","level-3_task"
                ));
                sendApiMethodAsync(message);
            }}
         // } else if (update.getCallbackQuery().getData().equals("glory_for-nation")) {
         // SendMessage message=createMessage("Смерть ворогам");
         //  message.setChatId(chatId);
         // sendApiMethodAsync(message);

         //}


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
    }
public void sendImage(String name, Long chatId){
    SendAnimation animation=new SendAnimation();
    InputFile inputFile=new InputFile();
    inputFile.setMedia(new File("images/"+name+".gif"));

    animation.setAnimation(inputFile);
    animation.setChatId(chatId);
    executeAsync(animation);
}
public int getLevel(Long chatId){
return levels.getOrDefault(chatId, 1);
}
public void setLevel(Long chatId, int level){
    levels.put(chatId,level);
}
}
