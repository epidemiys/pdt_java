package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("dfdfdf", "cxcx", "scxcxc"));
        submitGroupCreation();
        returnToGroupPage();
    }
}
