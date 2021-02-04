package tests;

import model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase{


    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().HomePage();
        if (app.contact().all().size() == 0){
            app.goTo().addNewContact();
            ContactData contact = new ContactData().withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withHome("1111").withMobile("222222").withWork("3333333").withEmail("email11@gmail.com").withEmail2("email222@gmail.com").withEmail3("email333@gmail.com");
            app.contact().create(contact, true);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

      //  assertThat(contact.getEmail(), equalTo(cleaned(contactInfoFromEditForm.getEmail())));
      //  assertThat(contact.getEmail2(), equalTo(cleaned(contactInfoFromEditForm.getEmail2())));
       // assertThat(contact.getEmail3(), equalTo(cleaned(contactInfoFromEditForm.getEmail3())));
    }

/*    public String cleaned(String email){
        //регулярные выражения. Заменяем регулярные выражения
        return email.replaceAll("\\s","").replaceAll("[-()]","");
    }*/

    private String mergeEmails(ContactData contact){
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s)-> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

}
