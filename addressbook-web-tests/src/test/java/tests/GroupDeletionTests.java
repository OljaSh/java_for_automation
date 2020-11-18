package tests;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper()
                .gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        }
        app.getGroupHelper()
                .selectGroup();
        app.getGroupHelper()
                .deleteSelectedGroup();
        app.getGroupHelper()
                .returnToGroupPage();
    }

}
