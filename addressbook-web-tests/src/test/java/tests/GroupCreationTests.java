package tests;

import model.GroupData;
import model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validGroups(){
        //заполняем список массивов
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator(); //при помощи этого Итератора тестовый фреймворк вытаскивает из списка по очереди один набор параметров за другим
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
       // GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.group().create(group);
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
        before.withAdded(group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2'");
        app.group().create(group);
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.group().count(), equalTo(before));
        Groups after = app.group().all();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before));
    }
}
