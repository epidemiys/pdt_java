package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToContactPage();
        ContactData contact = new ContactData("Александр", "Петров", null, null, null, "test1");
        app.getContactHelper().fillContactForm((contact), true);
        app.getContactHelper().submitContactCreationForm();
        app.getNavigationHelper().returnFromCreationPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2)-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }
}
