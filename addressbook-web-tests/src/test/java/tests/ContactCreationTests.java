package tests;

import model.ContactData;
import model.Contacts;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase{

    @Test
    public void testAddNewContact() throws Exception {
        Contacts before = app.contact().all();
        app.goTo().addNewContact();
        ContactData contact = new ContactData().withFirst_name("FitsName").withLast_name("LastName").withGroup("test1").withMobile("5674792392839").withHome("23525").withWork("6769");
        app.contact().create(contact, true);
        app.goTo().HomePage();
        //Хеширование - предварительная проверка перед более тяжелой операцией
        assertThat(app.contact().count(), Matchers.equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));

    }
}
