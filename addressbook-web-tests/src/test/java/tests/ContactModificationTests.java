package tests;

import model.ContactData;
import org.testng.annotations.BeforeMethod;

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
