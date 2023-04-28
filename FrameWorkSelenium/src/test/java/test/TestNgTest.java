package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SearchPage;

public class TestNgTest {

    @BeforeMethod()
    public void beforeTest() {
        System.out.println("Before");
    }

    @Test(groups = {"a", "b"})
    public void start1() {
        System.out.println("Test1");
    }

    @Test(groups = {"a"}, dependsOnMethods = {"start1"}, retryAnalyzer = listeners.RetryAnalyzer.class)
    @Parameters({"Name"})
    public void start2(@Optional("Tolia") String name) {
        System.out.println("Hello " + name);
        Assert.fail();
    }

    @AfterClass()
    public void afterTest() {
        System.out.println("After Test");
    }
}
