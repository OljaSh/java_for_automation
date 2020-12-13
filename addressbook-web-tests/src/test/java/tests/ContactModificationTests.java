package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().HomePage();
         if (app.contact().list().size() == 0){
            app.goTo().addNewContact();
             ContactData contact = new ContactData()
                     .withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName");
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactModification(){
         List<ContactData> before = app.contact().list();
           int index = (before.size() - 1);
        app.contact().initModifyContact(index);
        ContactData contact = new ContactData()
                .withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName");
        app.contact().fillContactForm((contact), false);
        app.contact().submitContactModification();
        app.goTo().HomePage();
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
