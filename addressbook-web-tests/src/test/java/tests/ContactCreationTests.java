package tests;

import model.ContactData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContacts(){
        //заполняем список массивов
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withFirst_name("FirstName1").withLast_name("LastName1").withGroup("test1")});
        list.add(new Object[] {new ContactData().withFirst_name("FirstName2").withLast_name("LastName2").withGroup("test2")});
        list.add(new Object[] {new ContactData().withFirst_name("FirstName3").withLast_name("LastName3").withGroup("test3")});
        return list.iterator(); //при помощи этого Итератора тестовый фреймворк вытаскивает из списка по очереди один набор параметров за другим
    }

    @Test(dataProvider = "validContacts")
    public void testAddNewContact(ContactData contact) throws Exception {
        app.goTo().HomePage();
        app.goTo().addNewContact();
     //   File photo = new File("src/test/resources/dog.png");
        app.contact().create(contact, true);
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
