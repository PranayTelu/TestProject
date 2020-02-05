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

public class Activity_9 {
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
    public void loginTest() {
    	//Open My Account page
    	WebElement myAccountMenu = driver.findElement(By.linkText("My Account"));
    	myAccountMenu.click();
    	
    	//Wait for page to load
    	wait.until(ExpectedConditions.titleIs("My Account – Alchemy LMS"));
    	
    	//Login
    	driver.findElement(By.className("ld-login")).click();
    	//Enter username
    	driver.findElement(By.id("user_login")).sendKeys("root");
    	//Enter password
    	driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
    	//click login
    	driver.findElement(By.id("wp-submit")).click();
    	
    	//Assertion
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Edit profile")));
    	WebElement editProfile = driver.findElement(By.linkText("Edit profile"));
    	Assert.assertTrue(editProfile.isDisplayed());
    }
    
    @Test(dependsOnMethods = "loginTest")
    public void completeLesson() {
    	String expectedTitle = "Deliverability Of Your Emails – Alchemy LMS";
    	
    	//Find All Courses and click it
    	driver.findElement(By.linkText("All Courses")).click();
    	
    	//Open the second course on the page
    	driver.findElement(By.cssSelector("#post-71 > div:nth-child(3) > p:nth-child(3) > a:nth-child(1)")).click();
    	
    	//Wait for course page to load
    	wait.until(ExpectedConditions.titleContains("Email Marketing Strategies"));
    	
    	//Click the first lesson in it
    	driver.findElement(By.cssSelector("#ld-expand-91 > div")).click();
    	
    	//Wait for lesson page to load
    	wait.until(ExpectedConditions.titleContains("Deliverability Of Your Emails"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), expectedTitle);
    	
    	//Find and click Mark Complete button
        //driver.findElement(By.className("learndash_mark_complete_button")).click();
    }

    @AfterTest
    public void afterTest() {
    	//Close browser
    	driver.close();
    }
}