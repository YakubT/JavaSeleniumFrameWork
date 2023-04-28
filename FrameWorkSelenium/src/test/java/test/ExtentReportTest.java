package test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import pages.SearchPage;


public class ExtentReportTest {

    private final static Logger LOGGER = LogManager.getLogger(ExtentReportTest.class);

    WebDriver driver;
    ExtentReports extent;
    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        extent = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("report1.html");
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testGoogleSearch() {
        ExtentTest test1 = extent.createTest("test1","Test searching in google");
        SearchPage searchPage = new SearchPage(driver);
        searchPage.open("https://www.google.com/");
        searchPage.search("Selenium");
        test1.pass("Test1 passed");
        LOGGER.info("HELLO");
    }

    @AfterClass
    public void tearDownTest() {
        extent.flush();
        driver.close();
        driver.quit();
    }

}
