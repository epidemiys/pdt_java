package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("000", "000", "000", "000", "000"));
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().returnToHomePage();

    }
}
