package test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

public class SecurityTest {
    private final static Logger LOGGER = LogManager.getLogger(GoogleTest.class);

    private final String zapProxyAddress = "localhost";

    private final int zapProxyPort = 8080;

    private final String zapApiKey = "s8t1uaop4cls42afmnhcpaoi7";

    private WebDriver driver;

    private ClientApi api;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        String proxyServerUrl = zapProxyAddress + ":" + zapProxyPort;
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyServerUrl);
        proxy.setSslProxy(proxyServerUrl);
        ChromeOptions options = new ChromeOptions();
        options.setProxy(proxy);
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        api = new ClientApi(zapProxyAddress, zapProxyPort, zapApiKey);
    }

    @Test
    public void testCodeForcesIssues() {
        driver.get("https://codeforces.com/");
    }

    @AfterClass
    public void tearDown() {
        String title = "Codeforces ZAP Security Report";
        String template = "traditional-html";
        String description = "This is codeforces security test report";
        String reportFileName = "codeforces-zap-report.html";
        String targetFolder = System.getProperty("user.dir");
        try {
            ApiResponse response = api.reports.generate(title, template, null, description,
                    null, null, null, null, null, reportFileName,
                    null, targetFolder, null);
            LOGGER.info("ZAP report generated in this location:"+response.toString());
        } catch (ClientApiException e) {
            LOGGER.error(e);
        }
        driver.quit();
    }
}
