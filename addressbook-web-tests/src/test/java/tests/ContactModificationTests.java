package tests;

import model.ContactData;
import model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().HomePage();
         if (app.contact().all().size() == 0){
            app.goTo().addNewContact();
             ContactData contact = new ContactData()
                     .withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName");
             app.contact().create(contact, false);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.contact().all();
           int index = (before.size() - 1);
        app.contact().initModifyContact(index);
        ContactData contact = new ContactData()
                .withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName");
        app.contact().fillContactForm((contact), false);
        app.contact().submitContactModification();
        app.goTo().HomePage();
        Contacts after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());

      /*  before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);*/
    }
}
