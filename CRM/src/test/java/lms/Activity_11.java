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

public class Activity_11 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeTest() {
        //Initialize firefox driver
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        
        //Open browser
        driver.get("https://alchemy.hguy.co/lms/my-account");

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
    
    @Test (priority = 0)
    public void openCourse() {
        //Open My Account page
        WebElement myAccountMenu = driver.findElement(By.linkText("All Courses"));
        myAccountMenu.click();
        
        //Wait for page to load
        wait.until(ExpectedConditions.titleContains("All Courses"));

        //Open the third course on the page
        driver.findElement(By.cssSelector("#post-24042 > div:nth-child(3) > p:nth-child(3)")).click();
        
        Assert.assertEquals(driver.getTitle(), "Content Marketing – Alchemy LMS");
    }
    
    @Test(dependsOnMethods = "openCourse", priority = 1)
    public void openLesson1() {
    	//Wait for page to load
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ld-section-heading")));
    	
    	//Open Topic
    	driver.findElement(By.cssSelector("#ld-expand-283")).click();
        
    	//Wait for page to load
        wait.until(ExpectedConditions.titleContains("Effective Writing & Promoting Your Content"));

        //Assertion
        Assert.assertEquals(driver.getTitle(), "Effective Writing & Promoting Your Content – Alchemy LMS");
        
        //Mark it as complete
        driver.findElement((By.className("learndash_mark_complete_button"))).click();
    }
    
    @Test (priority = 2)
    public void openLesson2Topics() {
    	//Wait for page to load
    	wait.until(ExpectedConditions.titleContains("Analyze Content & Develop Writing Strategies"));
    	
    	//Open Topic 1
    	driver.findElement(By.cssSelector("#ld-table-list-item-289 > a")).click();
        
    	//Wait for page to load
        wait.until(ExpectedConditions.titleContains("Growth Hacking With Your Content"));

        //Assertion
        Assert.assertEquals(driver.getTitle(), "Growth Hacking With Your Content – Alchemy LMS");
        
        //Mark Topic 1 as complete
        driver.findElement((By.className("learndash_mark_complete_button"))).click();
        
    	//Wait for page to load
        wait.until(ExpectedConditions.titleContains("The Power Of Effective Content"));

        //Assertion
        Assert.assertEquals(driver.getTitle(), "The Power Of Effective Content – Alchemy LMS");
        
        //Mark Topic 2 as complete
        driver.findElement((By.className("learndash_mark_complete_button"))).click();
        
        //Wait for lesson page to load
        wait.until(ExpectedConditions.titleContains("Analyze Content & Develop Writing Strategies"));
        
        //Assert lesson title
        Assert.assertEquals(driver.getTitle(), "Analyze Content & Develop Writing Strategies – Alchemy LMS");
        
        //Mark Lesson as complete
        driver.findElement((By.className("learndash_mark_complete_button"))).click();
    }
    
    @Test(priority = 3)
    public void courseAssertion() {
	    //Wait for course page to load
	    wait.until(ExpectedConditions.titleContains("Content Marketing"));
	    
	    //Assert course progress
	    String courseProgress = driver.findElement(By.className("ld-progress-percentage")).getText();
	    Assert.assertEquals(courseProgress, "100% COMPLETE");
    }

    @AfterClass
    public void afterTest() {
        //Close browser
        driver.close();
    }
}