package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class AddNewContactTests extends TestBase {

  @Test ()
  public void testAddNewContact()  {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
            withEmail("creat1@test.ru").withFirstname("Testr1").withMiddlename("Testri").withLastname("trtt").withNickname("Testi").withCompany("Test").
            withAddress("Test").withHomephone("98643567").withMobile("8944556632").withWork("Test1").withGroup("test1");

    app.contact().create(contact, true);
    app.goTo().homePage();
    assertEquals(app.contact().count(), before.size()+1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
