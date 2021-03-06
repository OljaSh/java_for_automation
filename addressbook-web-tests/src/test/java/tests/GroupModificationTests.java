package tests;

import model.GroupData;
import model.Groups;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo()
                .groupPage();
        if (app.group()
                .all()
                .size() == 0) {
            app.group()
                    .create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group()
                .all();
        GroupData modifiedGroup = before.iterator()
                .next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withName("Test1")
                .withHeader("test2")
                .withFooter("test3");
        app.group()
                .modify(group);
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.group().count(), Matchers.equalTo(before.size()));
        Groups after = app.group()
                .all();
        assertThat(after, equalTo(before.without(modifiedGroup)
                .withAdded(group)));
    }

    @Test
    public void testGroupModificationWithDB() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withName("Test1")
                .withHeader("test2")
                .withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(group);
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup)
                .withAdded(group)));
        verifyGroupListInUI();
    }

    public void verifyGroupListInUI() {
    }

}
