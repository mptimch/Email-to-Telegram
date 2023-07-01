import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import javax.mail.*;

public class Main {
    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException, MessagingException, InterruptedException, ExecutionException {

        // основные настройки для подключения к почте
        String user = "Your Email login";
        String password = "Your Email password";
        String host = "imap.yandex.ru"; // Check for this setting for your Email provider
        int port = 993;
        String telegramToken = "Your Email token";
        String chatId = "Chat ID where you want to get Emails";

        for (; ; ) {
            String textToTelegram = "";
            try {
                // receive a message from E-mail, convert it to text
                Callable<String> callable = new GetEmailMessage(user, password, host, port, logger);
                FutureTask<String> futureTask = new FutureTask<>(callable);
                new Thread(futureTask).start();
                textToTelegram = futureTask.get(60, TimeUnit.MINUTES);

            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            // sending letter to Telegram
            if (!textToTelegram.isEmpty()) {
                SendMessageToTelegram sendMessage = new SendMessageToTelegram(telegramToken, chatId, textToTelegram, logger);
                sendMessage.run();
            }
        }
    }
}

