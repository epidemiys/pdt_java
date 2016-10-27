package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
        type(By.name("mobile"), contactData.getMobilePhone());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if(contactData.getGroups().size() > 0){
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void fillForm1(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobilePhone());
        attach(By.name("photo"), contactData.getPhoto());
    }

    public void submitCreation() {
        click(By.name("submit"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        row.findElement(By.xpath(".//td[8]/a")).click();
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

    public void create(ContactData contactData) {
        navi.contact();
        fillForm(contactData, true);
        submitCreation();
        contactCache = null;
        navi.contactPage();
    }

    public void modifyContact(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillForm(contact, false);
        submitContactModificationForm();
        contactCache = null;
    }

    public void delete(ContactData сontact) {
        selectContactById(сontact.getId());
        initContactDeletion();
        submitDeletionForm();
        contactCache = null;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmail = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    //.withGroup("test1")
                    .withAllPhones(allPhones)
                    .withAddress(address)
                    .withAllEmail(allEmail);

            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFormEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withAddress(address)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);

    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //wd.findElement(By.xpath(String.format("//input[value='%s']./../../td[8]/a",id))).click();
        //wd.findElement(By.xpath(String.format("//tr[.//input[value='%s']]/td[8]/a"))).click();
        //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }

    public ContactData infoProfileForm(ContactData contact) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", contact.getId()))).click();
        String details = wd.findElement(By.id("content")).getText();
        wd.navigate().back();
        return new ContactData().withDetails(details);
    }
}
