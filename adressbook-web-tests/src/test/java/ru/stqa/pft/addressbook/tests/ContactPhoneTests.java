package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by aleksandr.petrov on 12.10.16.
 */
public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(contactInfoFormEditForm.getHomePhone()));
        assertThat(contact.getWorkPhone(), equalTo(contactInfoFormEditForm.getWorkPhone()));
        assertThat(contact.getMobilePhone(), equalTo(contactInfoFormEditForm.getMobilePhone()));
    }
}
