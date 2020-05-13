package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact()  {
    List<ContactData> before = app.contactHelper().getContactList();
    ContactData contact = new ContactData("creat1@test.ru", "Testr1", "Testri", "trtt", "Testi", "Test", "Test ", "97", "8911777", "test1","test1" );
    app.contactHelper().createContact(contact, true);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.contactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
