package tests;

import model.ContactData;
import model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();
        app.goTo().HomePage();
        if (app.contact().all().size() == 0){
            app.goTo().addNewContact();
            ContactData contact = new ContactData().withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withAddress("Street 1, Tallinn").withHome("1111").withMobile("222222").withWork("3333333").inGroup(groups.iterator().next());
            app.contact().create(contact, true);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactAddress(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(address(contactInfoFromEditForm)));
    }

    //метод обратных проверок, склеиваем что есть и сравниваем
    private String  address(ContactData contact) {
        return contact.getAddress();
    }

}
