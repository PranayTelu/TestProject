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

public class Activity_9 {
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

        //Click on the "Job Listings" menu item
        driver.findElement(By.cssSelector("a.menu-icon-job_listing")).click();

        //Click the "Add New" button
        driver.findElement(By.cssSelector(".page-title-action")).click();

        //Fill in the job listing details
        //Set the Job Title
        driver.findElement(By.cssSelector("#post-title-0")).sendKeys("Systems Engineer");

        //Click the Description editor
        driver.findElement(By.xpath("//div[@data-block]")).click();

        //Set the Job Description
        driver.findElement(By.xpath("//div[@data-block]/div[@contenteditable='true']")).sendKeys("Looking for someone to handle day to day systems operations.");

        //Set the Job Location
        driver.findElement(By.id("_job_location")).sendKeys("Hyderabad");

        //Set the Company Name
        driver.findElement(By.id("_company_name")).sendKeys("Alchemy");

        //Click the "Document" menu item
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[5]/div[1]/div/div/div/div[4]/div/div[2]/ul/li[1]/button")).click();

        //Set it to a full time job
        driver.findElement(By.xpath("//*[@id=\"in-job_listing_type-2\"]")).click();

        //Set it so that it is "Pending Review"
        driver.findElement(By.xpath("//*[@class=\"components-checkbox-control__input\"]")).click();

        //Click "Publish"
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[5]/div[1]/div/div/div/div[1]/div[2]/button[2]")).click();

        //Click "Publish" again
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[5]/div[1]/div/div/div/div[3]/div/div/div[1]/div/button")).click();

    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}