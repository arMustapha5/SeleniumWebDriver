package Base;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GoogleSearchTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    @Test
    void testGoogleSearch() {
        // Go to Google
        driver.get("https://www.google.com");
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        // Find and type in search box
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");

        // Submit search
        searchBox.submit();

        // Wait for search results to appear
        // This CSS selector targets the first search result link
        By firstResultLocator = By.cssSelector("div.g h3");
        wait.until(ExpectedConditions.presenceOfElementLocated(firstResultLocator));

        // Find and click the first result
        WebElement firstResult = driver.findElement(firstResultLocator);
        System.out.println("Clicking on: " + firstResult.getText());
        firstResult.click();

        // Wait for new page to load and print its title
        wait.until(ExpectedConditions.not(ExpectedConditions.titleContains("Google Search")));
        System.out.println("New page title: " + driver.getTitle());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}