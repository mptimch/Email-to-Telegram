import org.apache.logging.log4j.Logger;


import java.net.*;
import java.nio.charset.StandardCharsets;

public class SendMessageToTelegram extends Thread {
    private String telegramToken;
    private String chatId;
    private String text;
    private Logger logger;


    public SendMessageToTelegram(String telegramToken, String chatId, String text, Logger logger) {
        this.telegramToken = telegramToken;
        this.chatId = chatId;
        this.text = text;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            // Handling Cyrillic characters and spaces
            String finalUrl = "https://api.telegram.org/bot" + telegramToken + "/sendMessage?chat_id=" +
                    chatId + "&text=" + URLEncoder.encode(text, StandardCharsets.UTF_8);

            // Создаем Get запрос
            URL url = new URL(finalUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            // Depending on the response code, we keep a log
            if (responseCode == 200) {
                logger.info("В чат отправлено сообщение: " + text);
            } else {
                logger.error("Ошибка! Код ответа " + responseCode +
                        "\nпытались отправить текст: " + text +
                        "\nпытались перейти по урл: " + finalUrl +
                        "\nПолучили сообщение: " + connection.getResponseMessage());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
