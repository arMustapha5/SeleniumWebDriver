package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.co.uk/");
        // driver.findElement()
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        WebElement p=driver.findElement(By.name("q"));
        p.sendKeys("Selenium Java");
        p.submit();
        driver.close();
        driver.quit();

    }
    public static void main(String[] args){
        BaseTest test = new BaseTest();
        test.setup();
    }
}
