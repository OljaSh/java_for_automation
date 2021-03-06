package tests;

import model.ContactData;
import model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator()
                .next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirst_name("EditFitsName").withMiddle_name("EditMiddleName").withLast_name("EditLastName").withNickname("EditNickName");
        app.contact().modify(contact);
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();


        assertThat(after, equalTo(before.without(modifiedContact)
                .withAdded(contact)));
    }



/*    @Test
    public void testContactModification(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator()
                .next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName");
        app.contact().modify(contact);
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.contact().count(), Matchers.equalTo(before.size()));
        Contacts after = app.contact().all();


        assertThat(after, equalTo(before.without(modifiedContact)
                .withAdded(contact)));
    }*/



}
