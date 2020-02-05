package jobs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_8 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest()
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Setup the wait time
        wait = new WebDriverWait(driver, 10);

        //Open the browser and navigate to http://alchemy.hguy.co/jobs/wp-admin
        driver.get("http://alchemy.hguy.co/jobs/wp-admin");

        //Maximize the browser
        driver.manage().window().maximize();
    }

    @Test()
    public void Test(){
        //Fill in the credentials shared
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");

        //Click on the "Log In" button
        driver.findElement(By.id("wp-submit")).click();

        //Verify that you're on the admin page
        Assert.assertEquals(driver.findElement(By.cssSelector(".wrap > h1:nth-child(1)")).getText(), "Dashboard");

    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}