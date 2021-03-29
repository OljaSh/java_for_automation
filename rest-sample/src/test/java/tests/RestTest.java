package tests;

import model.Issue;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTest extends TestBase{


    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = app.rest()
                .getIssues();
        Issue newIssue = new Issue().withSubject("Test issue")
                .withDescription("New test issue");
        int issueId = app.rest()
                .createIssue(newIssue);
        Set<Issue> newIssues = app.rest()
                .getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    private void assertEquals(Set<Issue> newIssues, Set<Issue> oldIssues) {
    }


}


















