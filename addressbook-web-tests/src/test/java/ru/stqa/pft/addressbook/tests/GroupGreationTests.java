package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupGreationTests extends TestBase {


  @Test
  public void testGroupGreation() {

    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test1", "test1", "test1"));
    app.submitGroupCreation();
    app.returnToGroupPage();

  }


}
