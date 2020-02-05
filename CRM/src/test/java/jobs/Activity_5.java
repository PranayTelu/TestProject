package jobs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_5 {
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
        //Find the "Jobs" menu item
        WebElement jobs_button = driver.findElement(By.cssSelector("#menu-item-24 > a:nth-child(1)"));

        //Click the Jobs menu item
        jobs_button.click();

        //Verify that the new page title is "Jobs – Alchemy Jobs"
        Assert.assertEquals(driver.getTitle(), "Jobs – Alchemy Jobs");
    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}