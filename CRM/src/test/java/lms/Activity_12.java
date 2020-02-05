package lms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity_12 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeTest() {
        //Initialize firefox driver
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        
        //Open browser
        driver.get("https://alchemy.hguy.co/lms/my-account");
        
        driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
    	//Enter username
    	driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("root");
    	//Enter password
    	driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys("pa$$w0rd");
    	//click login
    	driver.findElement(By.xpath("//input[@value = 'Log In']")).click();
    	
    	//Assertion
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Edit profile")));
    	WebElement editProfile = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/main/article/div/section[2]/div[2]/div[2]/div[2]/div[2]/div/div[1]/div[1]/a"));
    	Assert.assertTrue(editProfile.isDisplayed());
    }
    
    @Test
    public void openCourse() {
        //Open My Account page
        WebElement myAccountMenu = driver.findElement(By.xpath("//a[contains(text(), 'All Courses')]"));
        myAccountMenu.click();
        
        //Wait for page to load
        wait.until(ExpectedConditions.titleContains("All Courses"));

        //Open the third course on the page
        driver.findElement(By.xpath("//p[@class='ld_course_grid_button']/a")).click();
        
        Assert.assertEquals(driver.getTitle(), "Social Media Marketing – Alchemy LMS");
    }
    
    @Test(dependsOnMethods = "openCourse")
    public void openLesson() {
    	//Wait for course page to load
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ld-section-heading")));
	    
	    //Find the third lesson and click it
    	driver.findElement(By.xpath("//div[contains(@id, 'ld-expand-87')]")).click();
    	
    	//Wait for lesson page to load
    	wait.until(ExpectedConditions.titleContains("Investment & Marketing Final Strategies"));
    	
    	//Assert page title
    	Assert.assertEquals(driver.getTitle(), "Investment & Marketing Final Strategies – Alchemy LMS");
    	
    	//Mark lesson as complete
    	driver.findElement((By.className("learndash_mark_complete_button"))).click();
    }
    
    @Test(priority = 3)
    public void progressAssertion() {
	    //Wait for course page to load
	    wait.until(ExpectedConditions.titleContains("Social Media Marketing"));
	    
	    //Assert course progress
	    String courseProgress = driver.findElement(By.xpath("//div[contains(@class, 'ld-progress-percentage')]")).getText();
	    Assert.assertEquals(courseProgress, "12% COMPLETE");
    }

    @AfterClass
    public void afterTest() {
        //Close browser
        driver.close();
    }
}