package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactCreationTests extends TestBase {

    @Test

    public void testContactCreation(){
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Александр", "Петров", "Москва", "java@mail.ru", "+79261112233"));
        app.getContactHelper().submitContactCreationForm();
        app.getNavigationHelper().returnFromCreationPage();
    }
}
