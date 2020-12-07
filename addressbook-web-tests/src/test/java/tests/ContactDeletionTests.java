package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().HomePage();
        if (app.contact().list().size() == 0){
            app.goTo().addNewContact();
            app.contact().create(new ContactData("First Name", "Middle Name", "Last Name", "Nickname", "title", "company", "address", "home", "mobile", "work", "test1"));
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactDeletion(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);

        List<ContactData> after = app.contact().list();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
