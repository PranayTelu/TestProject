package jobs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_2 {
    private WebDriver driver;

    @BeforeTest()
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser and navigate to http://alchemy.hguy.co/jobs
        driver.get("http://alchemy.hguy.co/jobs");

        //Maximize the browser
        driver.manage().window().maximize();
    }

    @Test()
    public void Test(){
        //Get the heading of the page
        String heading = driver.findElement(By.cssSelector("h1.entry-title")).getText();

        // Verify that the heading matches "Welcome to Alchemy Jobs"
        Assert.assertEquals(heading, "Welcome to Alchemy Jobs");
    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}
