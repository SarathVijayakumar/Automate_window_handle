package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDbAutomation {
    WebDriver driver;

    public IMDbAutomation() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void testCase01() {
        try {
            driver.get("https://www.imdb.com/chart/top");

            // Wait for the title column elements to load
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[@class='titleColumn']/a")));

            // Task 1: Find the highest rated movie on IMDb
            WebElement highestRatedMovie = driver.findElement(By.xpath("(//td[@class='titleColumn']/a)[1]"));
            String highestRatedMovieTitle = highestRatedMovie.getText();
            System.out.println("Highest rated movie: " + highestRatedMovieTitle);

            // Task 2: Find the number of movies in the table
            int movieCount = driver.findElements(By.xpath("//td[@class='titleColumn']")).size();
            System.out.println("Number of movies in the table: " + movieCount);

            // Task 3: Find the oldest movie on the list
            WebElement oldestMovie = driver.findElement(By.xpath("(//td[@class='titleColumn']/a)[last()]"));
            String oldestMovieTitle = oldestMovie.getText();
            System.out.println("Oldest movie on the list: " + oldestMovieTitle);

            // Task 4: Find the most recent movie on the list
            WebElement recentMovie = driver.findElement(By.xpath("(//td[@class='titleColumn']/a)[1]"));
            String recentMovieTitle = recentMovie.getText();
            System.out.println("Most recent movie on the list: " + recentMovieTitle);

            // Task 5: Find the movie with the most user ratings
            WebElement mostRatedMovie = driver.findElement(By.xpath("(//td[@class='ratingColumn']/strong)[1]"));
            String mostRatedMovieTitle = mostRatedMovie.getText();
            System.out.println("Movie with the most user ratings: " + mostRatedMovieTitle);
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
        IMDbAutomation automation = new IMDbAutomation();
        automation.testCase01();
        automation.endTest();
    }
}
