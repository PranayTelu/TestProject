package jobs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_6 {
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
        //Find the "Jobs" menu item
        WebElement jobs_button = driver.findElement(By.cssSelector("#menu-item-24 > a:nth-child(1)"));

        //Click the Jobs menu item
        jobs_button.click();

        //Search for "Banking" jobs
        driver.findElement(By.cssSelector("#search_keywords")).sendKeys("Banking");
        driver.findElement(By.cssSelector(".search_submit > input:nth-child(1)")).click();

        //Wait for jobs to load
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("job_listing"), 0));

        //Click on the first listing
        driver.findElement(By.cssSelector(".job_listings > li:nth-child(1)")).click();

        //Verify the job posting heading
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='entry-title']")).getText(), "Banking Financial Analyst");

        //Click the "Apply" button
        driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/div/div/div[3]/input")).click();

        //Print the email address to the console
        System.out.println(driver.findElement(By.xpath("//a[@class='job_application_email']")).getText());

    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}