package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws InterruptedException {
        if (!app.contactHelper().isThereAContact()){
            app.contactHelper().createContact(new ContactData("fordelete@test.ru", "fordelete", "fordelete", "Testovich1", "Testik1", "Test1", "Test 1-6-9545", "9999997", "89117777767", "test2","test1" ), true);
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.contactHelper().getContactList();
        app.contactHelper().selectContact(before.size() - 1);
        app.contactHelper().deleteContact();
        Thread.sleep(2000);
        List<ContactData> after = app.contactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }
}
