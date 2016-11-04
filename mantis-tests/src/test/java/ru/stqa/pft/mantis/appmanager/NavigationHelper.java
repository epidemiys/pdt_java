package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.tests.TestBase;

/**
 * Created by aleksandr.petrov on 04.11.16.
 */
public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void managePage() {
        click(By.cssSelector("input[value='управление']"));
    }

    public void manageUsers() {
        click(By.cssSelector("input[value='Управление пользователями']"));
    }

    public void user() {
        click(By.cssSelector("input[value='user1478259089681']"));
    }

    public void resetUserPassword() {
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }
}
