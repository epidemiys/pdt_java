package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        Contacts before = app.contact().all();
        File photo = new File("src/tests/resources/test.JPG");
        ContactData contact = new ContactData()
                .withLastname("Петров")
                .withFirstname("Александр")
                .withGroup("test1")
                .withPhoto(photo);

        app.goTo().contact();
        app.contact().fillForm((contact), true);
        app.contact().submitCreation();
        app.goTo().contactPage();

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.print(currentDir.getAbsolutePath());
        File photo = new File("src/tests/resources/test.JPG");
        System.out.print(photo.getAbsolutePath());
        System.out.print(photo.exists());
    }

    @Test (enabled = false)
    public void testBedContactCreation(){
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withLastname("Петров'")
                .withFirstname("Александр")
                .withGroup("test1");

        app.goTo().contact();
        app.contact().fillForm((contact), true);
        app.contact().submitCreation();
        app.goTo().contactPage();

        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
