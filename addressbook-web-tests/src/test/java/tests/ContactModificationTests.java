package tests;

import model.ContactData;
import model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        ContactData modifiedContact = before.iterator()
                .next();
        //app.contact().initModifyContact(index);
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName");
      // app.contact().fillContactForm(contact, false);
        app.contact().modify(contact);
      //  app.contact().submitContactModification();
       // app.goTo().HomePage();
        Contacts after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact)
                .withAdded(contact)));
    }



}
