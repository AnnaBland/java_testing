package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


public class RemoveContactFromGroup extends TestBase {

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
    public void testRemoveContactFromGroup() {
        Contacts contactsBefore = app.db().contacts();
        Groups groupsBefore = app.db().groups();
        ContactData contactForRemove = null;
        GroupData targetGroup = null;


        for (ContactData contact : contactsBefore) {
            Groups contactGroups = contact.getGroups();
            if (contactGroups.size() > 0) {
                contactForRemove = contact;
                targetGroup = contactForRemove.getGroups().iterator().next();
                break;
            } else {
                app.goTo().homePage();
                GroupData group = groupsBefore.iterator().next();
                app.contact().addToGroup(contact.getId(), contact, group);
            }
        }

        for (ContactData contact : contactsBefore) {
            Groups contactGroups = contact.getGroups();
            for (GroupData group : groupsBefore) {
                if (contactGroups.contains(group)) {
                    targetGroup = group;
                    contactForRemove = contact;
                    break;
                }
            }
        }

        app.goTo().homePage();
        app.contact().removeFromGroup(contactForRemove, targetGroup);
        verifyContactListInUI();


    }




}
