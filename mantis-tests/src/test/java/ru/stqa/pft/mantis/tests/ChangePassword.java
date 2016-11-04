
package ru.stqa.pft.mantis.tests;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by aleksandr.petrov on 04.11.16.
 */

public class ChangePassword extends TestBase {

    private SessionFactory sessionFactory;
    @BeforeClass
    public void setUpDb() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testPasswordReset() throws IOException, MessagingException {
        HttpSession session = app.newSession();
        User user = app.db().user();
        String username = user.getUsername();
        String email = user.getEmail();
        String newpassword = "newpassword";
        app.registration().loginAs("administrator", "root");
        app.registration().initiatePasswordReset(username);

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, newpassword);
        assertTrue(app.newSession().login(username, newpassword));
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }



}