package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }

    public void initiatePasswordReset(String username) {
        wd.get(app.getProperty("web.baseUrl") + "/my_view_page.php");
        click(By.linkText("управление"));
        click(By.linkText("Управление пользователями"));
        click(By.linkText(username));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void loginAs(String login, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/my_view_page.php");
        type(By.name("username"), login);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }

}