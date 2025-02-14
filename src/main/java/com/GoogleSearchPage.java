package com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GoogleSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Page Factory locators
    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(css = "div.g h3")
    private WebElement firstResult;

    // Constructor
    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        PageFactory.initElements(driver, this);
    }

    // Page methods
    public void navigateToGoogle() {
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
    }

    public void searchFor(String searchText) {
        searchBox.sendKeys(searchText);
        searchBox.submit();
    }

    public void clickFirstResult() {
        wait.until(ExpectedConditions.visibilityOf(firstResult));
        System.out.println("Clicking on: " + firstResult.getText());
        firstResult.click();
    }

    public String getCurrentPageTitle() {
        wait.until(ExpectedConditions.not(ExpectedConditions.titleContains("Selenium WebDriver")));
        return driver.getTitle();
    }

    public boolean verifySearchResult(String expectedTitle) {
        return driver.getTitle().equals(expectedTitle);
    }
}