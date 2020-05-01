package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact()  {
    app.contactHelper().createContact(new ContactData("creat1@test.ru", "Test1", "Testoi", "tr", "Testi", "Test", "Test ", "97", "8911777", "test1","test1" ), true);
    app.getNavigationHelper().gotoHomePage();
  }

}
