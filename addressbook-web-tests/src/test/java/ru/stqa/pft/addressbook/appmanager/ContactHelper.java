package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.NoSuchElementException;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super (wd);
    }

    public void submitAddNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation ) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"),contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("company"),contactData.getCompany());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getHomephone());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("work"),contactData.getWork());
        type(By.name("email"),contactData.getEmail());


        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }  else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void AddNewContact() {
        click (By.linkText("add new"));
    }


    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {

        click(By.cssSelector(".left:nth-child(8) > input"));
        clickAlert();

    }

    public void initContactModification() {
        click(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img"));
    }

    public void submitContactModify() {
        click(By.name("update"));
    }
}
