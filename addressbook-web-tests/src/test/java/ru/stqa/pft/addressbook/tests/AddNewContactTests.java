package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class AddNewContactTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContact() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line !=null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json,new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((g)-> new Object[] {g}).collect (Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContact")
  public void testAddNewContact(ContactData contact)  {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    //File photo = new File("src/test/resources/s1200.png");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertEquals(app.contact().count(), before.size()+1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }



}
