package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


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


    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {

        click(By.xpath("//input[@value='Delete']"));
        clickAlert();

    }

    public void initContactModification (int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();

    }

    public void submitContactModify() {
        click(By.name("update"));
    }

    public void createContact(ContactData contactData, boolean b) {
        AddNewContact();
       fillContactForm(contactData, b);
        submitAddNewContact();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element:elements) {
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact =new ContactData( id,null, firstname, null, lastname, null, null, null, null, null, null,null );
            contacts.add(contact);
        }

        return contacts;
    }
}
