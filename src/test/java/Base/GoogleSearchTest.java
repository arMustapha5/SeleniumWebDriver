package Base;

import com.GoogleSearchPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleSearchTest {
    private WebDriver driver;
    private GoogleSearchPage googlePage;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        googlePage = new GoogleSearchPage(driver);
    }

    @Test
    void testGoogleSearch() {
        String expectedResult = "WebDriver | Selenium";

        // Using POM methods to perform the test
        googlePage.navigateToGoogle();
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        googlePage.searchFor("Selenium WebDriver");
        googlePage.clickFirstResult();

        System.out.println("New page title: " + googlePage.getCurrentPageTitle());

        if (googlePage.verifySearchResult(expectedResult))
            System.out.println("Search has passed");
        else
            System.out.println("Search has Failed");

        // Add wait time after verifying the result
        try {
            System.out.println("Waiting for 5 seconds before closing...");
            Thread.sleep(5000); // Waits for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        driver.quit();
    }
}