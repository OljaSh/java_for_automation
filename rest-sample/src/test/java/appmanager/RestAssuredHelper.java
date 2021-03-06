package appmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import model.Issue;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public class RestAssuredHelper {

    private ApplicationManager app;

    public RestAssuredHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getIssues() {
        String json = RestAssured.get(app.getProperty("bugify.url") + "issues.json").asString();
        JsonElement parsedJson = new JsonParser().parse(json);
        JsonElement issues = parsedJson.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,  new TypeToken<Set<Issue>>() {}.getType());
    }

    public int createIssue(Issue issue) {
        String json = RestAssured.given()
                .parameter("subject", issue.getSubject())
                .parameter("description", issue.getDescription())
                .post(app.getProperty("bugify.url") + "issues.json").asString();
        JsonElement parsedJson = new JsonParser().parse(json);
        return parsedJson.getAsJsonObject().get("issue_id").getAsInt();
    }

    public Issue getIssue(int issueId) {

        class IssueDeserializer implements JsonDeserializer<Issue> {
            @Override
            public Issue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                JsonObject jObject = json.getAsJsonObject();
                return new Issue()
                        .withId(jObject.get("id").getAsInt())
                        .withSubject(jObject.get("subject").getAsString())
                        .withDescription(jObject.get("description").getAsString())
                        .withStatus(jObject.get("state_name").getAsString());
            }
        }

        String json = RestAssured.get(app.getProperty("bugify.url")
                + String.format("issues/%s.json", issueId)).asString();
        JsonElement parsedJson = new JsonParser().parse(json);
        JsonElement issuesJson = parsedJson.getAsJsonObject().get("issues");
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Issue.class, new IssueDeserializer())
                .create();
        List<Issue> issues = gson.fromJson(issuesJson, new TypeToken<List<Issue>>() {}.getType());
        return issues.get(0);
    }
}
