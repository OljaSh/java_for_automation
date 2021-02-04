package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroup extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo()
                .HomePage();
        if (app.db()
                .contacts()
                .size() == 0) {
            app.goTo()
                    .addNewContact();
            app.contact()
                    .create(new ContactData().withFirst_name("first test1")
                            .withLast_name("last test1"), true);
            app.goTo()
                    .HomePage();
        }

        if (app.db()
                .groups()
                .size() == 0) {
            app.goTo()
                    .groupPage();
            app.group()
                    .create(new GroupData().withName("Name test1"));
            app.goTo()
                    .HomePage();
        }
    }

    @Test
    public void TestAddContactToGroup() {
        Contacts listContacts = app.db()
                .contacts();
        ContactData selectedContact = listContacts.iterator()
                .next();
        Groups listGroups = app.db()
                .groups();
        GroupData selectedGroup = listGroups.iterator()
                .next();

        app.goTo()
                .HomePage();
        if (!selectedContact.getGroups()
                .isEmpty() && selectedContact.getGroups()
                .contains(selectedGroup)) {
            app.contact()
                    .removeContactFromGroup(selectedContact, selectedGroup);

            assertThat(selectedContact.getGroups()
                    .without(selectedGroup), equalTo(app.db()
                    .contacts()
                    .stream()
                    .filter((c) -> c.getId() == selectedContact.getId())
                    .collect(toList())
                    .get(0)
                    .getGroups()));

            app.contact()
                    .returnToHomePage();
        }

        app.contact()
                .selectDisplayGroup("[all]");
        app.contact()
                .addContactToGroup(selectedContact, selectedGroup);

        assertThat(selectedContact.getGroups()
                .withAdded(selectedGroup), equalTo(app.db()
                .contacts()
                .stream()
                .filter((c) -> c.getId() == selectedContact.getId())
                .collect(toList())
                .get(0)
                .getGroups()));
    }


}
