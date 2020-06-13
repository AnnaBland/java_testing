package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser(); //email server
    }

    public List<MailMessage> waitForMail(int count, long timeout) throws MessagingException, IOException {
        long start = System.currentTimeMillis();//remember current time
        while (System.currentTimeMillis() < start + timeout) {//assert new currentTimeMillis() < start + timeout
            if (wiser.getMessages().size() >= count) {//if email has enough letters
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());//transformation real objects to model MailMessage
            }
            try {//if email has few letters
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail");
    }

    public static MailMessage toModelMail(WiserMessage m) {//transformation real objects to model
        try {
            MimeMessage mm = m.getMimeMessage();//take real object
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());//take recipient list and get first of them
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start() {wiser.start();}

    public  void stop() {wiser.stop();}

}