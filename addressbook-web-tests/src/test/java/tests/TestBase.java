package tests;

import appmanager.ApplicationManager;
import model.GroupData;
import model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m){
        logger.info("Start test" + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p){
        logger.info("Stop test" + m.getName() + " with parameters " + Arrays.asList(p));
    }

    public void verifyGroupListInUI() {
        //добавляем флаг для проверки в зависимости от значения которого мы будем это запускать или нет
        if(Boolean.getBoolean("verifyUI")){

        }
        Groups dbGroups = app.db().groups();
        Groups uiGroups = app.group().all();
        //преобразуем множества, упрощаем объекты из BD т.к. из пользовательского интерфейса мы получаем меньше значений
        assertThat(uiGroups, equalTo(dbGroups.stream()
                .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                .collect(Collectors.toSet())));
    }
}
