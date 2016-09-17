import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class myFirst {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("https://m.my.mail.ru/cgi-bin/login?page=https%3A%2F%2Fm.my.mail.ru%2F");
        wd.findElement(By.name("Login")).click();
        wd.findElement(By.name("Login")).clear();
        wd.findElement(By.name("Login")).sendKeys("testqaqa1@mail.ru");
        wd.findElement(By.name("Password")).click();
        wd.findElement(By.name("Password")).clear();
        wd.findElement(By.name("Password")).sendKeys("Mytesting");
        wd.findElement(By.xpath("//div[@class='heightContent']/form/div[3]/input")).click();
    }
    
    @Test
    public void MyFirstTest() {
        wd.findElement(By.xpath(".//*[@id='leftmenu']/div[2]/a/span")).click();
        wd.findElement(By.cssSelector("a.profile-menu__tile.m-video")).click();
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
