package tests;

import model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactPhoneTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().HomePage();
        if (app.contact().all().size() == 0){
            app.goTo().addNewContact();
            ContactData contact = new ContactData().withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withNickname("NickName").withMobile("934974097");
            app.goTo().HomePage();
        }
    }


    @Test
    public void testContactPhones(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator()
                .next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }

}
