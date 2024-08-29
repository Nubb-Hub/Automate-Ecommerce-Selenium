
package com.example.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
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

public class Day5Test {
    public String firstname = "nub";
    public String lastname = "hub";
    WebDriver driver;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testCreateAccountAndShareWishlist() {

        // 1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // 2. Click on My Account link
        driver.findElement(By.linkText("MY ACCOUNT")).click();

        // 3. Click Create Account link and fill New User information except Email ID
        driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
        driver.findElement(By.id("firstname")).sendKeys(firstname);
        driver.findElement(By.id("lastname")).sendKeys(lastname);
        driver.findElement(By.id("email_address")).sendKeys("nubhub.404@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Nubhub123");
        driver.findElement(By.id("confirmation")).sendKeys("Nubhub123");

        // 4. Click Register
        driver.findElement(By.xpath("//button[.='Register']")).click();

        // 5. Verify Registration is done
        String expectedWelcomeMessage = "WELCOME, " + firstname + " " + lastname + "!";
        WebElement welcomeMessageElement = driver.findElement(By.className("welcome-msg"));
        String actualWelcomeMessage = welcomeMessageElement.getText();
        Assert.assertTrue(actualWelcomeMessage.equalsIgnoreCase(expectedWelcomeMessage), "Welcome message does not match.");

        //String successMessage = driver.findElement(By.className("success-msg")).getText();
        //Assert.assertTrue(successMessage.contains("Thank you for registering with Main Website Store."));

        // 6. Go to TV menu
        driver.findElement(By.linkText("TV")).click();

        // 7. Add product in your wish list LG LCD
        driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();

        // 8. Click SHARE WISHLIST
        driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

        // 9. In next page enter Email and a message and click SHARE WISHLIST
        driver.findElement(By.id("email_address")).sendKeys("nubhub.404@gmail.com");
        driver.findElement(By.id("message")).sendKeys("check this out.");
        driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

        // 10. Check wishlist is shared
        String shareSuccessMessage = driver.findElement(By.className("success-msg")).getText();
        Assert.assertTrue(shareSuccessMessage.contains("Your Wishlist has been shared."));
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
            String fileName = "Day5Test_" + testName + "_" + status + "_" + timestamp + ".png";
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
