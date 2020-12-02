package tests;

import model.GroupData;
import model.Groups;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
       Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1");
        app.group().create(group);
        Set<GroupData> after = app.group().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
    }
}
