package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact()  {

    app.gotoAddNewContactPage();
    app.fillContactForm(new ContactData("proba@test.ru", "Test1", "Testov", "Testovich1", "Testik1", "Test1", "Test 1-6-9545", "9999997", "89117777767", "test2"));
    app.submitAddNewContact();
    app.gotoHomePage();

  }

}
