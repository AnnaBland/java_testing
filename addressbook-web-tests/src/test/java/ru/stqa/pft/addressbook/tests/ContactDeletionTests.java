package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (!app.contactHelper().isThereAContact()){
            app.contactHelper().createContact(new ContactData("fordelete@test.ru", "fordelete", "fordelete", "Testovich1", "Testik1", "Test1", "Test 1-6-9545", "9999997", "89117777767", "test2","test1" ), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.contactHelper().selectContact();
        app.contactHelper().deleteContact();
    }
}
