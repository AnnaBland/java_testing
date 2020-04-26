package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase {
    @Test
    public void testContactModification()  {

        app.contactHelper().initContactModification();
        app.contactHelper().fillContactForm(new ContactData("modify@test.ru", "Test51", "Testovi", "Testovich1", "Testik1", "Test1", "Test 1-6-9545", "9999997", "89117777767", "test2"));
        app.contactHelper().submitContactModify();
        app.getNavigationHelper().gotoHomePage();


    }

}
