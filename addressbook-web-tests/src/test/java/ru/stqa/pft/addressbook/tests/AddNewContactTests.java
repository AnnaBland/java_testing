package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact()  {

    app.contactHelper().AddNewContact();
    app.contactHelper().fillContactForm(new ContactData("proba@test.ru", "Test50", "Testovi", "Testovich1", "Testik1", "Test1", "Test 1-6-9545", "9999997", "89117777767", "test2","test1" ), true);
    app.contactHelper().submitAddNewContact();
    app.getNavigationHelper().gotoHomePage();

  }

}
