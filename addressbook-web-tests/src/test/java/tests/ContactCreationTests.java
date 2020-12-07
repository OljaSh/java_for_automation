package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testAddNewContact() throws Exception {
        List<ContactData> before = app.contact().list();
        app.goTo().addNewContact();
        ContactData contact = new ContactData("First Name", "Middle Name", "Last Name", "Nickname", "title", "company", "address", "home", "mobile", "work", "test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }
}
