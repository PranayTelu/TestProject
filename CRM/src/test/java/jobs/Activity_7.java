package jobs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_7 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest()
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Setup the wait time
        wait = new WebDriverWait(driver, 10);

        //Open the browser and navigate to http://alchemy.hguy.co/jobs
        driver.get("http://alchemy.hguy.co/jobs");

        //Maximize the browser
        driver.manage().window().maximize();
    }

    @Test()
    public void Test(){
        //Find the "Post a Job" menu item
        WebElement jobs_button = driver.findElement(By.cssSelector("#menu-item-26 > a:nth-child(1)"));

        //Click on it
        jobs_button.click();

        //Click on the "Sign in" button
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/main/article/div/form/fieldset[1]/div/a")).click();

        //Wait until the log in page loads
        wait.until(ExpectedConditions.urlContains("wp-login"));

        //Verify that you have moved to the sign in page
        Assert.assertEquals(driver.getCurrentUrl(), "https://alchemy.hguy.co/jobs/wp-login.php?redirect_to=https%3A%2F%2Falchemy.hguy.co%2Fjobs%2Fpost-a-job%2F");

        //Fill in the credentials shared
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");

        //Click on the "Log In" button
        driver.findElement(By.id("wp-submit")).click();

        //Wait until the page redirects back
        wait.until(ExpectedConditions.urlContains("post-a-job"));

        //Fill up the details of the job posting
        //Enter Job Title
        driver.findElement(By.id("job_title")).sendKeys("Systems Engineer");

        //Enter Job Location
        driver.findElement(By.id("job_location")).sendKeys("Hyderabad");

        //Enter Job Type
        Select job_type = new Select(driver.findElement(By.id("job_type")));
        job_type.selectByVisibleText("Full Time");

        //Enter into the "Description" iframe to enter the description
        driver.switchTo().frame(0);

        //Enter the description
        driver.findElement(By.cssSelector("#tinymce")).sendKeys("Looking for someone to handle day to day systems operations.");

        //Leave the iframe and go back to default window
        driver.switchTo().defaultContent();

        //Clear the company name field
        driver.findElement(By.id("company_name")).clear();

        //Enter the company name
        driver.findElement(By.id("company_name")).sendKeys("Alchemy");

        //Click the "Preview" button
        driver.findElement(By.cssSelector("input.button:nth-child(4)")).click();

        //Wait for the page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job_listing_preview")));

        //Verify that the heading matches your job title
        Assert.assertEquals(driver.findElement(By.cssSelector(".job_listing_preview > h1:nth-child(1)")).getText(), "Systems Engineer");

        //Click on the "Submit Listing" button
        driver.findElement(By.id("job_preview_submit_button")).click();

    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}