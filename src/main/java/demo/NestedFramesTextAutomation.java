package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NestedFramesTextAutomation {
    WebDriver driver;

    public NestedFramesTextAutomation() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void testCase01() {
        try {
            driver.get("https://the-internet.herokuapp.com/nested_frames");

            // Find the top frame
            driver.switchTo().frame("frame-top");

            // Find the left frame within the top frame
            driver.switchTo().frame("frame-left");
            WebElement leftFrameText = driver.findElement(By.tagName("body"));
            System.out.println("Text in left frame: " + leftFrameText.getText());

            // Switch back to the top frame
            driver.switchTo().parentFrame();

            // Find the middle frame within the top frame
            driver.switchTo().frame("frame-middle");
            WebElement middleFrameText = driver.findElement(By.tagName("body"));
            System.out.println("Text in middle frame: " + middleFrameText.getText());

            // Switch back to the top frame
            driver.switchTo().parentFrame();

            // Find the right frame within the top frame
            driver.switchTo().frame("frame-right");
            WebElement rightFrameText = driver.findElement(By.tagName("body"));
            System.out.println("Text in right frame: " + rightFrameText.getText());

            // Switch back to the default content
            driver.switchTo().defaultContent();

            // Find the bottom frame
            driver.switchTo().frame("frame-bottom");
            WebElement bottomFrameText = driver.findElement(By.tagName("body"));
            System.out.println("Text in bottom frame: " + bottomFrameText.getText());
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
        NestedFramesTextAutomation automation = new NestedFramesTextAutomation();
        automation.testCase01();
        automation.endTest();
    }
}
