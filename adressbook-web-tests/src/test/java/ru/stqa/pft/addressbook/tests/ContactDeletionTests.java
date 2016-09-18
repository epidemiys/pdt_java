package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactDeletionTests extends TestBase {

    @Test

    public void testContactDeletion(){
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().submitDeletionForm();
        app.getNavigationHelper().returnToHomePage();

    }
}
