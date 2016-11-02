package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by aleksandr.petrov on 02.11.16.
 */
public class RegistrationTests extends TestBase {

    @Test

    public void testRegistration(){
        app.registration().start("user1","user1@localhost.localdomain");
    }
}
