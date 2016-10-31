package ru.stqa.pft.addressbook.tests;

        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addressbook.model.ContactData;
        import ru.stqa.pft.addressbook.model.Contacts;
        import ru.stqa.pft.addressbook.model.GroupData;
        import ru.stqa.pft.addressbook.model.Groups;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by aleksandr.petrov on 29.10.16.
 */
public class ContactAddToGroupTests extends TestBase{

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
            app.contact().create(new ContactData().withLastname("Last_name_test").withFirstname("First_name_test").withAddress("Address_test")
                    .withHomePhone("123456789").withEmail("email@mail.ru"));
        }

        // create group if contact presents in all groups
        Groups contactGroups = app.db().contacts().iterator().next().getGroups();
        Groups allGroups = app.db().groups();
        if (contactGroups.equals(allGroups)) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
    }


    @Test
    public void testContactAddToGroup() {
        Contacts beforeContacts = app.db().contacts();
        ContactData contact = beforeContacts.iterator().next();

        Groups groups = app.db().groups();
        groups.removeAll(contact.getGroups());
        GroupData group = groups.iterator().next();

        app.goTo().homePage();
        app.contact().addContactToGroup(contact, group);

        Contacts afterContacts = app.db().contacts();
        ContactData contactWithGroup = afterContacts.iterator().next();

        assertThat(contactWithGroup.getGroups(), equalTo(contact.getGroups().withAdded(group)));
    }
}