package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData("First Name", "Middle Name", "Last Name", "Nickname", "title", "company", "address", "home", "mobile", "work", "test1"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getNavigationHelper().gotoHomePage();
    }
}
