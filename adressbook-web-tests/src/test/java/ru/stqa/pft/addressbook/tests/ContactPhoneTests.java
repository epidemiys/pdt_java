package ru.stqa.pft.addressbook.tests;

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

        assertThat(contact.getHomePhone(), equalTo(cleanedPhone(contactInfoFormEditForm.getHomePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleanedPhone(contactInfoFormEditForm.getWorkPhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleanedPhone(contactInfoFormEditForm.getMobilePhone())));
        assertThat(contact.getAddress(), equalTo(cleanedText(contactInfoFormEditForm.getAddress())));
        assertThat(contact.getEmail(), equalTo(cleanedText(contactInfoFormEditForm.getEmail())));

    }

    public String cleanedPhone (String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]" , "");
    }

    public String cleanedText (String text) {
        return text.replaceAll("  ", "");
    }
}
