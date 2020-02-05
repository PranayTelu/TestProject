package lms;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity_14 {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor JSExecutor;
    Actions builder;

    @BeforeClass
    public void beforeTest() {
        //Initialize firefox driver
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        JSExecutor = (JavascriptExecutor) driver;
        builder = new Actions(driver);
        
        //Open browser
        driver.get("https://alchemy.hguy.co/lms/my-account");
        
        driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
    	//Enter username
    	driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("root");
    	//Enter password
    	driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys("pa$$w0rd", Keys.RETURN);
    	
    	//Assertion
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Edit profile")));
    	WebElement editProfile = driver.findElement(By.linkText("Edit profile"));
    	Assert.assertTrue(editProfile.isDisplayed());
    }
    
    @Test(priority = 0)
    public void openCourseWithKeyboard() {
    	//Scroll page down
    	JSExecutor.executeScript("window.scrollBy(0, 300)");
    	
    	WebElement chosenCourse = driver.findElement(By.id("ld-course-list-item-71"));
    	Action openCourse = builder.sendKeys(chosenCourse, Keys.RETURN).build();
    	openCourse.perform();
    	
    	//wait for page to load
    	wait.until(ExpectedConditions.titleContains("Email Marketing Strategies"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "Email Marketing Strategies – Alchemy LMS");
    }
    
    public void markComplete() {
    	WebElement markCompleteButton = driver.findElement(By.className("learndash_mark_complete_button"));
    	Action pressMarkCompleteButton = builder.sendKeys(markCompleteButton, Keys.RETURN).build();
    	pressMarkCompleteButton.perform();
    }
    
    @Test(priority = 1, dependsOnMethods = "openCourseWithKeyboard")
    public void openLesson1WithKeyboard() {
    	//Scroll page down
    	JSExecutor.executeScript("window.scrollBy(0, 500)");
    	
    	WebElement lessonOne = driver.findElement(By.id("ld-expand-91"));
    	Action openLesson = builder.sendKeys(lessonOne, Keys.RETURN).build();
    	openLesson.perform();
    	
    	//wait for page to load
    	wait.until(ExpectedConditions.titleContains("Deliverability Of Your Emails"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "Deliverability Of Your Emails – Alchemy LMS");
    	
    	//Mark the lesson 1 as Complete
    	markComplete();
    }
    
    @Test(priority = 2, dependsOnMethods = "openCourseWithKeyboard")
    public void openLesson2WithKeyboard() {
    	//Scroll page down
    	JSExecutor.executeScript("window.scrollBy(0, 500)");
    	
    	//wait for page to load
    	wait.until(ExpectedConditions.titleContains("Improving & Designing Marketing Emails"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "Improving & Designing Marketing Emails – Alchemy LMS");
    	
    	//Mark the lesson 2 as Complete
    	markComplete();
    }
    
    @Test(priority = 3)
    public void checkCourseProgress() {
    	//wait for course page to load
    	wait.until(ExpectedConditions.titleContains("Email Marketing Strategies"));
    	
    	//Assert Course progress
    	String courseProgress = driver.findElement(By.xpath("//div[contains(@class, 'ld-progress-percentage')]")).getText();
    	Assert.assertEquals(courseProgress, "100% COMPLETE");
    }

    @AfterClass
    public void afterTest() {
        //Close browser
        driver.close();
    }
}