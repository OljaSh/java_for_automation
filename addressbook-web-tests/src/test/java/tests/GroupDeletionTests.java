package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper()
                .gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper()
                .selectGroup(before.size() - 1);
        app.getGroupHelper()
                .deleteSelectedGroup();
        app.getGroupHelper()
                .returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }

}
