package tests;

import com.thoughtworks.xstream.XStream;
import model.GroupData;
import model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        //заполняем список массивов
       // List<Object[]> list = new ArrayList<Object[]>();
       // list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
       //делаем через чтение из файла
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"));
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            //обработка строк
           // String[] split =  line.split(";");
            //создаем массив который состоит из одного элемента
           // list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
          //  line = reader.readLine();
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        //преведение типа
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        //каждый объект заворачиваем в массив. Потом из потока собираем список и у списка берем итератор
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        //return list.iterator(); //при помощи этого Итератора тестовый фреймворк вытаскивает из списка по очереди один набор параметров за другим
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
