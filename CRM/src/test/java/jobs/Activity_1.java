package jobs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_1 {
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
        //Get the title of the page
        String title = driver.getTitle();

        // Verify that the title matches "Alchemy Jobs – Job Board Application"
        Assert.assertEquals(title, "Alchemy Jobs – Job Board Application");
    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}
