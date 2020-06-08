package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;



public class ContactModification extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() throws Exception{
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withEmail("creat1@test.ru").withFirstname("Testr1").withMiddlename("Testri").withLastname("trtt").withNickname("Testi").withCompany("Test").
                            withAddress("Test").withHomephone("98643567").withMobile("8944556632").withWork("Test1"), true);
        }
    }



    @Test()
    public void testContactModification()  throws  Exception{

        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).
                withEmail("tr@test.ru").withFirstname("MODIF").withMiddlename("Middle").withLastname("Last").withNickname("Testi").withCompany("Test").
                withAddress("Test").withHomephone("98643567").withMobile("8944556632").withWork("Test1");
        app.goTo().homePage();
        app.contact().modify(contact);
       assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(contact).without(modifiedContact)));
        verifyContactListInUI();

    }


}
