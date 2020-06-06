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
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("work"),contactData.getWork());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());
//        attach(By.name("photo"),contactData.getPhoto());

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

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        wd.findElement(By.linkText("home")).click();
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
        initModificationById(contact.getId());
        fillForm(contact,false);
        submitModify();
        contactCahce = null;
        gotoHomePage();
    }
    public void initModificationById(int id)  {
        wd.findElement (By.xpath(String.format("//a[@href='edit.php?id=%s']",id))).click();
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
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element:elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String allPhones= cells.get(5).getText();
            String allEmails=cells.get(4).getText();
            String address = cells.get(3).getText();
            contactCahce.add(new ContactData().withId(id).withAllEmails(allEmails).withFirstname(firstname).withMiddlename("Testri").withLastname(lastname)
                    .withNickname("Testi").withCompany("Test").withAddress(address).withAllPhones(allPhones));
        }
        return new Contacts (contactCahce);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initModificationById(contact.getId());
        String firstname = wd.findElement (By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement (By.name("lastname")).getAttribute("value");
        String home = wd.findElement (By.name("home")).getAttribute("value");
        String mobile = wd.findElement (By.name("mobile")).getAttribute("value");
        String work = wd.findElement (By.name("work")).getAttribute("value");
        String email= wd.findElement (By.name("email")).getAttribute("value");
        String email2 = wd.findElement (By.name("email2")).getAttribute("value");
        String email3 = wd.findElement (By.name("email3")).getAttribute("value");
        String address = wd.findElement (By.name("address")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname).withHomephone(home).withMobile(mobile).withWork(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }
}

