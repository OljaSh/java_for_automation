package tests;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{
    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper()
                .gotoGroupPage();
        app.getGroupHelper()
                .initGroupCreation("new");
        app.getGroupHelper()
                .fillGroupForm(new GroupData("test1", "test2", null));
        app.getGroupHelper()
                .submitGroupCreation("submit");
        app.getGroupHelper()
                .returnToGroupPage();
        app.clickLogout("Logout");
    }
}
