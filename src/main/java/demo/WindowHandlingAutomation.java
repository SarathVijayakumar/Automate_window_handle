package demo;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils; // For saving the screenshot as a file
import java.io.File; //

public class WindowHandlingAutomation {
    private WebDriver driver;

    public WindowHandlingAutomation() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

public void testCase01() {
    try {
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

        // Switch to the iframe containing the "Try it" button
        driver.switchTo().frame("iframeResult");

        // Find and click the "Try it" button
        WebElement tryItButton = driver.findElement(By.xpath("//button[text()='Try it']"));
        tryItButton.click();

        // Get the handles of the open windows
        String originalWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);

                // Get the URL and title
                String newWindowTitle = driver.getTitle();
                String newWindowUrl = driver.getCurrentUrl();

                // Capture a screenshot
                TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
                byte[] screenshotBytes = screenshotDriver.getScreenshotAs(OutputType.BYTES);

                // Save the screenshot to a file (you can specify the path)
                File screenshotFile = new File("screenshot.png");
                FileUtils.writeByteArrayToFile(screenshotFile, screenshotBytes);

                System.out.println("New Window Title: " + newWindowTitle);
                System.out.println("New Window URL: " + newWindowUrl);
                System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());

                // Close the new window
                driver.close();
            }
        }

        // Switch back to the original window
        driver.switchTo().window(originalWindowHandle);
    } catch (Exception e) {
        System.err.println("Error in testCase01: " + e.getMessage());
    }
}


    public void endTest() {
        try {
            driver.quit();
        } catch (Exception e) {
            System.err.println("Error in endTest: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        WindowHandlingAutomation automation = new WindowHandlingAutomation();
        automation.testCase01();
        automation.endTest();
    }
}

