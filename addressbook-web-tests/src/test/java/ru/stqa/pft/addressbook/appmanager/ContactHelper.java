package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void submitAddNewContact() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("home")).sendKeys(contactData.getHomephone());
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        wd.findElement(By.name("work")).sendKeys(contactData.getWork());
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void AddNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }
}
