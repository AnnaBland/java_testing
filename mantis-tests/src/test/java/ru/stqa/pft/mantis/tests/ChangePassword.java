package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePassword extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }


    @Test
    public void testChangePassword() throws IOException, MessagingException {
        Users mantisUsers = app.db().users();
        UserData user = mantisUsers.iterator().next();
        app.registration().adminLogin();
        app.registration().resetUserPassword(user.getId());
        List<MailMessage> mailMessages = app.mail().waitForMail(1,60000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        String password = "password";
        app.registration().finish(confirmationLink,  password );
        assertTrue(app.newSession().login(user.getUsername(), password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
