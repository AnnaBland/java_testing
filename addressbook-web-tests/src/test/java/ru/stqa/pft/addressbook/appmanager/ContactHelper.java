package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;



public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super (wd);
    }

    public void submitAddNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillForm(ContactData contactData, boolean creation ) {
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

    public void addNewContact() {
        click (By.linkText("add new"));
    }


    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id+"']")).click();
    }

    public void delete() {

        click(By.xpath("//input[@value='Delete']"));
        clickAlert();

    }


    public void delete(ContactData contact) throws InterruptedException {
        selectById(contact.getId());
        delete();
        contactCahce = null;
        Thread.sleep(2000);
    }



    public void submitModify() {
        click(By.name("update"));
    }

    public void create (ContactData contact, boolean b) {
        addNewContact();
       fillForm(contact, b);
        submitAddNewContact();
        contactCahce = null;

    }

    public void modify(ContactData contact)  {
        initModification(contact.getId());
        fillForm(contact,false);
        submitModify();
        contactCahce = null;
    }
    public void initModification(int id)  {
        wd.findElement (By.xpath("//a[@href='edit.php?id=" + id+"']")).click();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

//    public boolean isThereAContact() {
//        return isElementPresent(By.name("selected[]"));
//    }

    private Contacts contactCahce = null;



    public Contacts all() {
        if (contactCahce !=null) {
            return new Contacts(contactCahce);
        }
        contactCahce = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element:elements) {
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contactCahce.add(new ContactData().withId(id).withEmail("creat1@test.ru").withFirstname(firstname).withMiddlename("Testri").withLastname(lastname)
                    .withNickname("Testi").withCompany("Test").withAddress("Test").withHomephone("98643567").withMobile("8944556632").withWork("Test1").withGroup("Test1"));
        }
        return new Contacts (contactCahce);
    }


    }

