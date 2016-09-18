package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by aleksandr.petrov on 17.09.16.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToContactPage() {
        click(By.xpath("//*[@id='nav']/ul/li[2]/a"));
    }

    public void returnToHomePage() {
        click(By.xpath("//*[@id='content']/div/i/a[2]"));
    }

    public void goToHomePage() {
        click(By.name("home"));
    }
}
