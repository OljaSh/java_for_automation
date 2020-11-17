package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
    @Test
    public void testAddNewContact() throws Exception {
        app.getNavigationHelper().gotoAddNewContact();
        app.getContactHelper().fillContactForm(new ContactData("First Name", "Middle Name", "Last Name", "Nickname", "title", "company", "address", "home", "mobile", "work", "test1"), true);
        app.getContactHelper().submitContactCreation("submit");
    }
}
