package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by aleksandr.petrov on 04.11.16.
 */
public class ChangePassword extends TestBase {

    @Test
    public void testLogin() throws IOException {
        assertTrue(app.newSession().login("administrator", "root"));
        app.goTo().managePage();
        app.goTo().manageUsers();
        app.goTo().user();
        app.goTo().resetUserPassword();
    }
}
