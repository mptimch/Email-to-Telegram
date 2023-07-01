import org.apache.logging.log4j.Logger;

import javax.mail.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.Callable;

public class GetEmailMessage implements Callable<String> {
    private String user;
    private String password;
    private String host;
    private int port;
    private Logger logger;

    public GetEmailMessage(String user, String password, String host, int port, Logger logger) throws InterruptedException, NoSuchProviderException {
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
        this.logger = logger;
    }

    @Override
    public String call() throws Exception {
        // create a session, set up a connection
        Session session = getSession(user, password, host, port);
        Store store = session.getStore();
        store.connect(host, port, user, password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);


        // waiting for inbox email
        int nowMessage = inbox.getMessageCount();
        while (inbox.getMessageCount() == nowMessage) {
            Thread.sleep(3);
        }

        // Getting data from an incoming email
        Message message = inbox.getMessage(inbox.getMessageCount());
        String subject = message.getSubject();
        String formattedDate = getCorrectDate(message);
        String messageText = getMessageText(message);

        // write a message according to a template that is convenient for us
//        String textToTelegram = "Дата отправки: " + formattedDate + "\nтема письма: " + subject +
//                "\nтекст письма: " + messageText;
        String textToTelegramForMe = "Дата отправки: " + formattedDate + "\nтема письма: " + subject;

        // mark message as read
        message.setFlag(Flags.Flag.SEEN, true);
        nowMessage++;
        logger.info("Забрали с почты сообщение " + textToTelegramForMe);
        return textToTelegramForMe;
    }


    private Session getSession(String user, String password, String host, int port) {
        Properties prop = System.getProperties();
        prop.put("mail.user", user);
        prop.put("mail.password", password);
        prop.put("mail.host", host);
        prop.put("mail.debug", "false");
        prop.put("mail.store.protocol", "imaps");
        prop.put("mail.imaps.ssl.protocols", "TLSv1.2");
        prop.put("mail.imaps.ssl.ciphersuites", "TLS_RSA_WITH_AES_128_CBC_SHA");
        prop.put("mail.imaps.ssl.trust", "*");
        Session session = Session.getInstance(prop);
        return session;
    }

    private String getCorrectDate(Message message) throws MessagingException {
        Date date = message.getSentDate();

        SimpleDateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'года' HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), inputFormat.getTimeZone().toZoneId());
        String formattedDate = dateTime.format(outputFormatter);
        return formattedDate;
    }

    private String getMessageText(Message message) throws MessagingException, IOException {
        String unformattedText = "";

        // часть писем прилетают в String, часть в Multipart. Поэтому делаем так
        try {
            unformattedText = (String) message.getContent();
        } catch (java.lang.ClassCastException exception) {
            Multipart multipart = (Multipart) message.getContent();
            BodyPart bodyPart = multipart.getBodyPart(0);
            unformattedText = (String) bodyPart.getContent();
        }
        if (unformattedText.isEmpty()) {
            return "";
        }
        String cleanedText = unformattedText.replaceAll("<div>|</div>", "");
        return cleanedText;
    }
}
