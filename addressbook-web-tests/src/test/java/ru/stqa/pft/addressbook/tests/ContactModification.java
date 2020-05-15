package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactModification extends TestBase {
    @Test
    public void testContactModification()  {

        if (! app.contactHelper().isThereAContact()){
            app.contactHelper().createContact(new ContactData("proba1@test.ru", "Test50", "Testovi", "Testovich1", "Testik1", "Test1", "Test 1-6-9545", "9999997", "89117777767", "test2","test1" ), true);
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.contactHelper().getContactList();
        app.contactHelper().initContactModification(before.size() -1);
        ContactData contact = new ContactData ("modify@test.ru", "l", "Testovi", "T", "Testik1", "Test1", "Test 1-6-9545", "9999997", "89117777767", "test2", null);
        app.contactHelper().fillContactForm(contact,false);
        app.contactHelper().submitContactModify();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.contactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
