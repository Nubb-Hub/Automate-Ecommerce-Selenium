
package com.example.test;

import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1Test {
    private WebDriver driver;


    @BeforeTest
    public void setUp() {
        // Set the path to your Chrome driver here
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    @Test
    public void verifyMobileSorting() {
        // Step 1: Goto http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Step 2: Verify Title of the page
        WebElement titleElement = driver.findElement(By.xpath("//h2[contains(.,'This is demo site for')]"));
        Assert.assertTrue(titleElement.isDisplayed(), "Title element with text 'This is demo site for' not found.");

        // Step 3: Click On 'MOBILE' menu
        WebElement mobileMenu = driver.findElement(By.xpath("//a[.='Mobile']"));
        mobileMenu.click();

        // Step 4: Verity Title of the page
        WebElement mobilePageTitleElement = driver.findElement(By.xpath("//h1[.='Mobile']"));
        Assert.assertTrue(mobilePageTitleElement.isDisplayed(), "Title 'Mobile' is not displayed on mobile pages.");

        // Step 5: In the list of alt mobile, select SORT BY dropdown as 'name'
        Select sortByDropdown = new Select(driver.findElement(By.cssSelector(".category-products > .toolbar [title='Sort By']")));
        sortByDropdown.selectByVisibleText("Name");

        // Step 6: Verify all products are sorted by name
        List<WebElement> productNamesElements = driver.findElements(By.xpath("//h2[@class='product-name']"));
        List<String> productNames = new ArrayList<>();
        for (WebElement element : productNamesElements) {
            productNames.add(element.getText());
        }

        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames);

        // Use Assert.assertEquals for list comparison
        Assert.assertEquals(productNames, sortedProductNames, "Products are not sorted by name. Expected list: " + sortedProductNames + ", actual list: " + productNames);

    }


        @AfterMethod
        public void captureScreenshot(ITestResult result) {
            String status = result.isSuccess() ? "success" : "failure";
            takeScreenshot(status, result.getName());
            driver.quit();
        }

        private void takeScreenshot (String status, String testName){
            try {
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                String timestamp = now.format(formatter);
                String fileName = "Day1Test_" + testName + "_" + status + "_" + timestamp + ".png";
                String relativePath = "c:\\guru99\\LPSelenium2-guru99\\screenshot";
                Path directoryPath = Paths.get(relativePath);
                Path destinationPath = Paths.get(directoryPath.toString(), fileName);
                Files.copy(screenshotFile.toPath(), destinationPath);
                System.out.println("Screenshots are saved in: " + destinationPath);
            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }
