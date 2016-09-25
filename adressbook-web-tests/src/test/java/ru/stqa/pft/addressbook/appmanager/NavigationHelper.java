package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by aleksandr.petrov on 17.09.16.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void goToContactPage() {
        click(By.xpath("//*[@id='nav']/ul/li[2]/a"));
    }

    public void returnFromModificationPage() {
        click(By.xpath("//*[@id='content']/div/i/a"));
    }

    public void goToHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void returnFromCreationPage() {
        click(By.xpath("//*[@id='nav']/ul/li[2]/a"));
    }
}
