package jobs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_4 {
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
        //Get the second heading on the site
        String second_heading = driver.findElement(By.cssSelector(".entry-content > h2:nth-child(6)")).getText();

        //Verify that the second heading is "Quia quis non"
        Assert.assertEquals(second_heading, "Quia quis non");
    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}
