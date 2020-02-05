package lms;
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

public class Activity_10 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        //Initialize firefox driver
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        
        //Open browser
        driver.get("https://alchemy.hguy.co/lms");
    }
    
    @Test
    public void openLesson() {
        //Open My Account page
        WebElement myAccountMenu = driver.findElement(By.linkText("All Courses"));
        myAccountMenu.click();
        
        //Wait for page to load
        wait.until(ExpectedConditions.titleContains("All Courses"));

        //Open the third course on the page
        driver.findElement(By.cssSelector("#post-24042 > div:nth-child(3) > p:nth-child(3)")).click();

        //Wait for course page to load
        wait.until(ExpectedConditions.titleContains("Content Marketing"));

        //Click login
        driver.findElement(By.linkText("Login")).click();
        //Enter username
        driver.findElement(By.id("user_login")).sendKeys("root");
        //Enter password
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        //click login
    	driver.findElement(By.id("wp-submit")).click();
        
        //Open lesson
        driver.findElement(By.cssSelector("#ld-expand-24187")).click();

        //Assertion
        Assert.assertEquals(driver.getTitle(), "Analyze Content & Develop Writing Strategies – Alchemy LMS");
    }
    
    @Test(dependsOnMethods = "openLesson")
    public void openTopic() {
    	//Open Topic
    	driver.findElement(By.cssSelector("#ld-table-list-item-289 > a")).click();
        
    	//Wait for page to load
        wait.until(ExpectedConditions.titleContains("Growth Hacking With Your Content"));

        //Assertion
        Assert.assertEquals(driver.getTitle(), "Growth Hacking With Your Content – Alchemy LMS");
        
        //Mark it as complete
        driver.findElement((By.className("learndash_mark_complete_button"))).click();
    }

    @AfterTest
    public void afterTest() {
        //Close browser
        driver.close();
    }
}