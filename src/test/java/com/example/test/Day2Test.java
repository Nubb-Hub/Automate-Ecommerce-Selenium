package com.example.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Day2Test {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void
    verifyProductCost() {
        // 1. Goto http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // 2. Click On 'MOBILE' menu
        driver.findElement(By.linkText("MOBILE")).click();

        // 3. In the list of all mobile, read the cost of Sony Xperia mobile, Note this value
        WebElement sonyXperiaPriceList = driver.findElement(By.xpath("//a[text()='Sony Xperia']/../..//span[@class='price']"));
        String listPrice = sonyXperiaPriceList.getText();

        // 4. Click on Sony Xperia mobile
        driver.findElement(By.cssSelector("[title='Sony Xperia']")).click();

        // 5. Read the Sony Xperia mobile from detail page.
        WebElement sonyXperiaPriceDetail = driver.findElement(By.xpath("//span[@class='price']"));
        String detailPrice = sonyXperiaPriceDetail.getText();

        // 6. Compare value in Step 3 & Step 5
        Assert.assertEquals(listPrice, detailPrice, "Product prices on the listing page and detail page are not the same.");
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        String status = result.isSuccess() ? "success" : "failure";
        takeScreenshot(status, result.getName());
        driver.quit();
    }
    private void takeScreenshot(String status, String testName) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            String fileName = "Day2Test_" + testName + "_" + status + "_" + timestamp + ".png";
            String relativePath = "c:\\guru99\\LPSelenium2-guru99\\screenshot"; // Change the path as needed
            Path directoryPath = Paths.get(relativePath);
            Path destinationPath = Paths.get(directoryPath.toString(), fileName);
            Files.copy(screenshotFile.toPath(), destinationPath);
            System.out.println("Screenshots are saved in: " + destinationPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
