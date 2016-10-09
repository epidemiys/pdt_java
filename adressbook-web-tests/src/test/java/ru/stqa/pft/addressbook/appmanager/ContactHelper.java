package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandr.petrov on 18.09.16.
 */
public class ContactHelper extends HelperBase {

    NavigationHelper navi = new NavigationHelper(wd);

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobile());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        if (isElementPresent(By.name("new_group"))) {
        }
    }

    public void submitCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        wd.findElements((By.xpath("//*[@id='maintable']//img[@title='Edit']"))).get(index).click();
    }

    public void initContactDeletion() {
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
    }

    public void submitDeletionForm() {
        wd.switchTo().alert().accept();
    }

    public void submitContactModificationForm() {
        click(By.name("update"));
    }

    public void createContact(ContactData contactData) {
        navi.contact();
        fillForm(contactData, true);
        submitCreation();
        navi.contactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    .withGroup("test1");
            
            contacts.add(contact);
        }
        return contacts;
    }

    public void modifyContact(int index, ContactData contact) {
        selectContact(index);
        initContactModification(index);
        fillForm(contact, false);
        submitContactModificationForm();
    }

    public void delete(int index) {
        selectContact(index);
        initContactDeletion();
        submitDeletionForm();
    }
}
