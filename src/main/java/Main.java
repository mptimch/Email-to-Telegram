import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import javax.mail.*;

public class Main {
    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException, MessagingException, InterruptedException, ExecutionException {

        Properties properties = new Properties();
        String currentDirectory = System.getProperty("user.dir");
        FileInputStream fileInputStream = new FileInputStream(currentDirectory + File.separator + "config.properties");
        properties.load(fileInputStream);
        fileInputStream.close();


        // Getting data from application.properties
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String telegramToken = properties.getProperty("telegramToken");
        String chatId = properties.getProperty("chatId");
        int port = Integer.parseInt(properties.getProperty("port"));
        MailServers mailServers = MailServers.valueOf(properties.getProperty("mail_service").toUpperCase(Locale.ROOT));
        String host = "";

        switch (mailServers) {
            case YANDEX -> host = "imap.yandex.ru";
            case MAIL -> host = "imap.mail.ru";
            case GMAIL -> host = "imap.gmail.com";
        }

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

