package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage extends AbstractPage {

    private final By searchBox = By.xpath("//div[@class='RNNXgb']");

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public String getTextFromSearchBox() {
        return driver.findElement(searchBox).getText();
    }
}
