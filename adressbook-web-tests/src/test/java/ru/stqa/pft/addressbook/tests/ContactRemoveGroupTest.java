package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by aleksandr.petrov on 31.10.16.
 */
public class ContactRemoveGroupTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        // groups
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }

        // contacts
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withLastname("Александр")
                    .withFirstname("Петров")
                    .withAddress("Улица Пушкина")
                    .withHomePhone("89261112233")
                    .withEmail("test@mail.ru"));
        }
    }

    @Test
    public void testContactRemoveGroup() {

        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();

        Contacts beforeContacts = app.db().contacts();
        ContactData contact;

        List<ContactData> contactsWithGroups = beforeContacts.stream().filter(c -> c.getGroups().size() != 0).collect(Collectors.toList());
        if (contactsWithGroups.size() == 0) {
            app.goTo().homePage();
            app.contact().addContactToGroup(beforeContacts.iterator().next(), group);

            beforeContacts = app.db().contacts();
            contact = beforeContacts.iterator().next();
        } else {
            contact = contactsWithGroups.iterator().next();
        }

        groups.retainAll(contact.getGroups());
        GroupData groupWithContact = groups.iterator().next();

        app.goTo().homePage();
        app.contact().removeContactFromGroup(contact, groupWithContact);

        Contacts afterContacts = app.db().contacts();
        ContactData afterContact = afterContacts.iterator().next();

        assertThat(afterContact.getGroups(), equalTo(contact.getGroups().without(groupWithContact)));
    }
}
