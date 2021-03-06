package tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import model.ContactData;
import model.Contacts;
import model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContactsFromCsv() throws IOException {
        //заполняем список массивов
        List<Object[]> list = new ArrayList<Object[]>();
        //  list.add(new Object[] {new ContactData().withFirst_name("FirstName1").withLast_name("LastName1").withGroup("test1")});
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
            String line = reader.readLine();
            while (line != null) {
                //обработка строк
                String[] split = line.split(";");
                //создаем массив который состоит из одного элемента
                list.add(new Object[]{new ContactData().withFirst_name(split[0])
                        .withLast_name(split[1]).getGroups().iterator().next().getName()});
                line = reader.readLine();
            }
            return list.iterator(); //при помощи этого Итератора тестовый фреймворк вытаскивает из списка по очереди один набор параметров за другим
        }
    }


    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType()); //означает тоже самое что List<GroupData>.close
            //каждый объект заворачиваем в массив. Потом из потока собираем список и у списка берем итератор
            return groups.stream()
                    .map((g) -> new Object[]{g})
                    .collect(Collectors.toList())
                    .iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testAddNewContact(ContactData contact) throws Exception {
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/dog.png");
        ContactData newContact = new ContactData()
                .withFirst_name("FitsName").withLast_name("LastName").inGroup(groups.iterator().next()).withPhoto(photo).withMobile("5674792392839").withHome("23525").withWork("6769");
        app.goTo().HomePage();
        app.goTo().addNewContact();
        app.contact().create(newContact, true);
       // app.contact().fillContactForm(
       //         new ContactData().withFirst_name("FitsName").withLast_name("LastName").withGroup("test1").withPhoto(photo).withMobile("5674792392839").withHome("23525").withWork("6769"), true);
       // app.contact().submitContactCreation();
    }

    @Test(enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        //как проверить путь к файлу
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/dog.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testAddNewContactWithDb(ContactData contact) throws Exception {
        Contacts before = app.db().contacts();
        app.goTo().HomePage();
        File photo = new File("src/test/resources/dog.png");
        app.contact().create(contact, true);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));
    }

/*    @Test()
    public void testAddNewContact() throws Exception {
        Contacts before = app.contact().all();
        app.goTo().addNewContact();
        ContactData contact = new ContactData().withFirst_name("FitsName").withLast_name("LastName").withGroup("test1").withMobile("5674792392839").withHome("23525").withWork("6769");
        app.contact().create(contact, true);
        app.goTo().HomePage();
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));
    }*/
}
