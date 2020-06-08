package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class AddContactToGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws  Exception{
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Precond"));
        }
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withEmail("creat1@test.ru").withFirstname("Testr1").withMiddlename("Testri").withLastname("trtt").withNickname("Testi").withCompany("Test").
                            withAddress("Test").withHomephone("98643567").withMobile("8944556632").withWork("Test1"), true);
        }


    }

    @Test
    public void testAddContactToGroup()  {
        {
            app.goTo().homePage();
            Contacts beforeContacts = app.db().contacts();
            ContactData contact = beforeContacts.iterator().next();
            Groups addedGroups = contact.getGroups();
            Groups beforeGroups = app.db().groups();
            Groups withoutAdded = new Groups();


            if (beforeGroups == addedGroups) {
                app.goTo().groupPage();
                GroupData newGroup = new GroupData().withName("new_group");
                app.group().create(newGroup);
                beforeGroups = app.db().groups();

            }
            for (GroupData group : beforeGroups)  {
                if (!addedGroups.contains(group)) {
                    withoutAdded.add(group);
                }
            }

            GroupData group = withoutAdded.iterator().next();
            app.contact().addToGroup(contact.getId(), contact, group);
            app.contact().gotoHomePage();

            verifyContactListInUI();
        }


}
}
