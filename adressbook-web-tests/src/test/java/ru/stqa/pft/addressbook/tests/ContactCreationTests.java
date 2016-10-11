package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withLastname("Петров")
                .withFirstname("Александр")
                .withGroup("test1");

        app.goTo().contact();
        app.contact().fillForm((contact), true);
        app.contact().submitCreation();
        app.goTo().contactPage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
