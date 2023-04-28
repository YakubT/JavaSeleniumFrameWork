package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage extends AbstractPage {

    By searchPanel = By.xpath("//div[@jscontroller='vZr2rb']/textarea");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public ResultPage search(String text) {
        driver.findElement(searchPanel).sendKeys(text);
        driver.findElement(searchPanel).sendKeys(Keys.RETURN);
        return new ResultPage(driver);
    }

    public void refresh() {
        driver.navigate().refresh();
    }
}
