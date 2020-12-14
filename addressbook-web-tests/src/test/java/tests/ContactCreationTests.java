package tests;

import model.ContactData;
import model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase{

    @Test
    public void testAddNewContact() throws Exception {
        Contacts before = app.contact().all();
        app.goTo().addNewContact();
        ContactData contact = new ContactData().withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().HomePage();
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));

    }
}
