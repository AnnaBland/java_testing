package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupGreationTests extends TestBase {


  @Test
  public void testGroupGreation() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test1", "test1"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();

  }


}
