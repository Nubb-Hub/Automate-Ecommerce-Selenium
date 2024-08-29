package com.example.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.time.Duration;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Day4Test {
    WebDriver driver;
    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void verifyProductComparison() {
        // 1. Goto http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // 2. Click menu 'MOBILE'
        driver.findElement(By.linkText("MOBILE")).click();

        // 3.a Click 'Add To Compare' for Sony Xperia
        driver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
        // 3.b Click 'Add To Compare' for IPHONE
        driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();

        // 4. Click 'COMPARE' button
        driver.findElement(By.cssSelector(".button[title='Compare']")).click();

        // 4.1 Get all open window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // 4.2 Iterate through the window handles and find the pop-up window handle
        String originalWindowHandle = driver.getWindowHandle();
        String popupWindowHandle = null;
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(driver.getWindowHandle())) { // If not the main window
                popupWindowHandle = windowHandle;
                break;
            }
        }

        // 4.3 Switch to pop-up window
        driver.switchTo().window(popupWindowHandle);

        // 5. Verify the pop-up window and check that the products are reflected in it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='page-title title-buttons'] h1")));
        String popupHeading = driver.findElement(By.cssSelector("div[class='page-title title-buttons'] h1")).getText();
        Assert.assertEquals(popupHeading, "COMPARE PRODUCTS", "The pop-up title is inappropriate.");
        Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='Sony Xperia']")).isDisplayed(), "Sony Xperia not found.");
        Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='IPhone']")).isDisplayed(), "IPHONE not found.");

        // take screenshot for compare
        takeScreenshot("success", "compare_products");

        // 6. Close the Popup Windows
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();

        // 6.1 Switch back to the original window
        driver.switchTo().window(originalWindowHandle);

    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        String status = result.isSuccess() ? "success" : "failure";
        takeScreenshot(status, result.getName());
    }
    private void takeScreenshot(String status, String testName) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            String fileName = "Day4Test_" + testName + "_" + status + "_" + timestamp + ".png";
            String relativePath = "c:\\guru99\\LPSelenium2-guru99\\screenshot"; // Change the path as needed
            Path directoryPath = Paths.get(relativePath);
            Path destinationPath = Paths.get(directoryPath.toString(), fileName);
            Files.copy(screenshotFile.toPath(), destinationPath);
            System.out.println("Screenshots are saved in: " + destinationPath);
        } catch (NoSuchWindowException e) {
            System.err.println("Window close. Failed to save screenshot.");
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
