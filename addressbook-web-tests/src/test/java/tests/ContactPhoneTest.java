package tests;

import model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().HomePage();
        if (app.contact().all().size() == 0){
            app.goTo().addNewContact();
            ContactData contact = new ContactData().withFirst_name("FitsName").withMiddle_name("MiddleName").withLast_name("LastName").withHome("1111").withMobile("222222").withWork("3333333").withGroup("test1");
            app.contact().create(contact, true);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        //old example
        //assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
    }

    //метод обратных проверок, склеиваем что есть и сравниваем
    private String  mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHome(), contact.getWork(), contact.getMobile())
               .stream().filter((s)-> ! s.equals(""))
               .map(ContactPhoneTest::cleaned)
               .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        //регулярные выражения. Заменяем регулярные выражения
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }


}
