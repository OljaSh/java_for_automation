package tests;

import model.ContactData;
import model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().HomePage();
        if (app.contact().all().size() == 0){
            app.goTo().addNewContact();
            ContactData contact = new ContactData().withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName");
            app.goTo().HomePage();
        }
    }


    @Test
    public void testContactDeletion(){
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

/*
    @Test
    public void testContactDeletion(){
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.contact().count(), Matchers.equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }*/



}
