package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void testAddContactToGroup() throws Exception {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        if (!contacts.stream().filter((s) -> (s.getGroups().size() < groups.size())).findAny().isPresent()) {
            app.contact().create(new ContactData().withFirstname("Testn").withLastname("Testn"), true);
        }
        ContactData addedContact =
                app.db().contacts().stream().filter((s) -> (s.getGroups().size() < groups.size())).findAny().get();
        Groups before = addedContact.getGroups();
        GroupData group = groups.without(addedContact.getGroups()).iterator().next();
        app.contact().addToGroup( group, addedContact);
        Groups after = app.db().contacts().stream().filter((s) -> s.equals(addedContact)).findFirst().get().getGroups();
        assertThat(after, equalTo(before.withAdded(group)));
}
}
