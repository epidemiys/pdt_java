package ru.stqa.pft.addressbook.tests;

        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addressbook.model.ContactData;
        import ru.stqa.pft.addressbook.model.GroupData;
        import ru.stqa.pft.addressbook.model.Groups;

/**
 * Created by aleksandr.petrov on 29.10.16.
 */
public class ContactAddToGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditionsGroups() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
    }

    @BeforeMethod
    public void ensurePreconditionsContacts() {
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withLastname("Александр")
                    .withFirstname("Петров")
                    .withAddress("Улица Пушкина")
                    .withHomePhone("123456789")
                    .withEmail("email@mail.ru")
                    .inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactAddToGroup(ContactData contact){
        app.goTo().homePage();
        app.contact().addContactToGroup(contact);
    }
}
