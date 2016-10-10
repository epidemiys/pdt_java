package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withLastname("Петров")
                .withFirstname("Александр")
                .withGroup("test1");

        app.goTo().contact();
        app.contact().fillForm((contact), true);
        app.contact().submitCreation();
        app.goTo().contactPage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }
}
