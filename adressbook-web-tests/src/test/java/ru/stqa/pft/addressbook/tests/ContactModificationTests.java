package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (! app.contact().isThereAContact()){
            app.contact().createContact(new ContactData()
                    .withFirstname("Александр")
                    .withFirstname("Петров")
                    .withGroup("test1"));
        }
    }

    @Test

    public void testContactModification(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Александр")
                .withLastname("Петров")
                .withGroup("test1");

        app.contact().modifyContact(contact);
        app.goTo().homePageFromModification();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
