
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

public class Day3Test {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testAddToCartError() {
        // 1. Goto http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // 2. Click on 'Mobile' menu
        driver.findElement(By.linkText("MOBILE")).click();

        // 3. Click on 'ADD TO CART' for Sony Xperia mobile
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/../..//button[@title='Add to Cart']")).click();

        // 4. Change 'QTY' value to 1000 and click 'UPDATE' button
        driver.findElement(By.xpath("//input[@title='Qty']")).clear();
        driver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("1000");
        driver.findElement(By.xpath("//button[@title='Update']")).click();

        // 5. Verify the error message
        String errorMessage = driver.findElement(By.xpath("//span[.='Some of the products cannot be ordered in requested quantity.']")).getText();
        Assert.assertEquals(errorMessage, "Some of the products cannot be ordered in requested quantity.");

        // 6. Click on 'EMPTY CART' link
        driver.findElement(By.xpath("//button[@id='empty_cart_button']/span[1]/span[.='Empty Cart']")).click();

        // 7. Verify cart is empty
        String emptyCartMessage = driver.findElement(By.xpath("//h1[.='Shopping Cart is Empty']")).getText();
        Assert.assertEquals(emptyCartMessage, "SHOPPING CART IS EMPTY");
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
            String fileName = "Day3Test_" + testName + "_" + status + "_" + timestamp + ".png";
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
