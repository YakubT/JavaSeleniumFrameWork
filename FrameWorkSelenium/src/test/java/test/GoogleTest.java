package test;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.ResultPage;
import pages.SearchPage;

public class GoogleTest {

    private final static Logger LOGGER = LogManager.getLogger(GoogleTest.class);

    WebDriver driver;

    Eyes eyes = new Eyes();
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        driver = new ChromeDriver();
        eyes.setApiKey("7LOxuzceJhCETBk75fn88Z9tCGh4VHUyp53szQRnsb4110");
        driver = new ChromeDriver();
        eyes.open(driver,"google search","google test",new RectangleSize(800,600));
    }

    @Test(dataProvider = "searchParam")
    public void testSearch(String text) {

        SearchPage searchPage = new SearchPage(driver);
        searchPage.open("https://www.google.com/");
        eyes.checkWindow("Hello");
        ResultPage resultPage = searchPage.search(text);
        eyes.checkWindow("Bye");
        Assert.assertEquals(resultPage.getTextFromSearchBox(), text);
    }

    @DataProvider(name = "searchParam")
    public Object[][] dataProviderSearch() {
        try {
            XSSFWorkbook workBook = new XSSFWorkbook("./src/test/resources/GoogleTest.xlsx");
            XSSFSheet sheet = workBook.getSheet("SeacrhParams");
            int k = sheet.getPhysicalNumberOfRows();
            LOGGER.info(k);
            Object[][] arr = new Object[k-1][1];
            for (int i = 1; i < k; i++) {
                arr[i-1][0] = sheet.getRow(i).getCell(0).getStringCellValue();
            }
            return arr;
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
        return null;
    }


    @AfterClass
    public void tearDown() {
        eyes.close();
        driver.close();
    }
}
