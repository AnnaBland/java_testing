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
        click(By.cssSelector("input[value='Signup']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    public void adminLogin() {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.xpath("//input[@value='Login']"));
    }

    public void resetUserPassword(int id) {
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
        click(By.xpath(String.format("//a[contains(@href, 'manage_user_edit_page.php?user_id=%s')]", id)));
        click(By.xpath("//input[@value='Reset Password']"));
    }
}
