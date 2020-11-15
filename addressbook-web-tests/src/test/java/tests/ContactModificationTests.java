package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initModifyContact();
        app.getContactHelper().fillContactForm(new ContactData("First1 Name1", "Middle1 Name1", "Last1 Name1", "Nickname1", "title1", "company1", "address1", "home1", "mobile1", "work1"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
