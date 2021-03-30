import com.google.common.collect.ImmutableMap;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.RepoCommit;
import com.jcabi.github.RepoCommits;
import com.jcabi.github.RtGithub;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("ea08f4472f6cc198659f28532c723e3b421a7526");
        RepoCommits commits = github.repos()
                .get(new Coordinates.Simple("OljaSh", "java_for_automation"))
                .commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) //строим пустой набор пар {
        {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
        }
}
