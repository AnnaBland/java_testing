package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupGreationTests extends TestBase {


  @Test
  public void testGroupGreation() throws Exception {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test1", "test1"));
    submitGroupCreation();
    returnToGroupPage();

  }


}
