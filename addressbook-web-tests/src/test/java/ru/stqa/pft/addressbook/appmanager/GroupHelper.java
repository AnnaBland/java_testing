package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {

        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
      click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.xpath("(//input[@name='new'])[2]"));
    }

    public void deleteSetectedGroups() {
        click(By.name("delete"));
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id+"']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

//    public boolean isThereAGroup() {
//        return isElementPresent(By.name("selected[]"));
//
//    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCahce = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCahce = null;
        returnToGroupPage();
    }


    public void delete(GroupData group) {
        selectById(group.getId());
        deleteSetectedGroups();
        groupCahce = null;
        returnToGroupPage();
    }

   public int count() {
       return wd.findElements(By.name("selected[]")).size();
    }


    private Groups groupCahce = null;

    public Groups all() {
        if (groupCahce != null)
        {
        return new Groups (groupCahce);
        }
        groupCahce = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element:elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCahce.add(new GroupData().withId(id).withName(name));
        }
        return new Groups (groupCahce);
    }



//    public boolean openHomePage() {
//        if (wd.findElement(By.id("maintable"));
//            return true;
//    }
}
