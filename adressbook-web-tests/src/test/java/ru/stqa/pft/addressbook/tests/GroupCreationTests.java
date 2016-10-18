package ru.stqa.pft.addressbook.tests;

import org.apache.xerces.xs.ItemPSVI;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();

    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
            app.goTo().groupPage();
            Groups before = app.group().all();
            app.group().initGroupCreation();
            app.group().fillGroupForm(group);
            app.group().submitGroupCreation();
            app.group().returnToGroupPage();
            assertThat(app.group().count(), equalTo(before.size() + 1));
            Groups after = app.group().all();
            assertThat(after, equalTo(before.withAdded(
                    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().initGroupCreation();
        GroupData group = new GroupData()
                .withName("test2'");
        app.group().fillGroupForm(group);
        app.group().submitGroupCreation();
        app.group().returnToGroupPage();
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));
    }
}
