package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SearchPage;

public class BrowserTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        SearchPage searchPage = new SearchPage(webDriver);
        searchPage.open("https://www.google.com/");
        searchPage.refresh();
    }
}
