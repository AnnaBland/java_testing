package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws  Exception {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Precond"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withEmail("creat1@test.ru").withFirstname("Testr1").withMiddlename("Testri").withLastname("trtt").withNickname("Testi").withCompany("Test").
                            withAddress("Test").withHomephone("98643567").withMobile("8944556632").withWork("Test1"), true);
        }

    }

    @Test
    public void testRemoveContactFromGroup() {
            Contacts contacts = app.db().contacts();
            Groups groups = app.db().groups();
            if (!contacts.stream().filter((s) -> (s.getGroups().size() > 0)).findAny().isPresent()) {
                app.contact().addToGroup(groups.iterator().next(), contacts.iterator().next());
            }
            ContactData removedContact = app.db().contacts().stream().filter((s) -> (s.getGroups().size() > 0)).findAny().get();
            Groups before = removedContact.getGroups();
            GroupData modifiedGroup = before.iterator().next();
            app.contact().removeFromGroup(modifiedGroup, removedContact);
            Groups after = app.db().contacts().stream().filter((s) -> s.equals(removedContact)).findFirst().get().getGroups();;
            assertThat(after, equalTo(before.without(modifiedGroup)));
    }
}
