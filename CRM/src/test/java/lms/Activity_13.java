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

public class Activity_13 {
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
    
    @Test(priority = 0)
    public void openCourse() {
        //Open My Account page
        WebElement myAccountMenu = driver.findElement(By.xpath("//a[contains(text(), 'All Courses')]"));
        myAccountMenu.click();
        
        //Wait for page to load
        wait.until(ExpectedConditions.titleContains("All Courses"));

        //Open the third course on the page
        driver.findElement(By.xpath("//p[@class='ld_course_grid_button']/a")).click();
        
        //wait for course page to load
        wait.until(ExpectedConditions.titleContains("Social Media Marketing"));

        Assert.assertEquals(driver.getTitle(), "Social Media Marketing – Alchemy LMS");
    }
    
    public void markComplete() {
    	driver.findElement(By.xpath("//input[@value = 'Mark Complete']")).click();
    }
    
    @Test(priority = 1, dependsOnMethods = "openCourse")
    public void openLesson1() {
    	//Open first lesson
    	driver.findElement(By.xpath("//div[@id = 'ld-expand-83']")).click();
    	
    	//wait for lesson page to open
    	wait.until(ExpectedConditions.titleContains("Developing Strategy"));
    	
    	//Open first topic
    	driver.findElement(By.xpath("//div[@id = 'ld-table-list-item-175']")).click();
    }
    
    @Test(priority = 2, dependsOnMethods = "openLesson1")
    public void openLesson1Topic1() {
    	//wait for topic 1 page to load
    	wait.until(ExpectedConditions.titleContains("This is the First Topic"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "This is the First Topic – Alchemy LMS");
    	
    	//Mark topic 1 as complete
    	markComplete();
    }
    
    @Test(priority = 3, dependsOnMethods = "openLesson1")
    public void openLesson1Topic2() {
    	//wait for topic 1 page to load
    	wait.until(ExpectedConditions.titleContains("Monitoring & Advertising"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "Monitoring & Advertising – Alchemy LMS");
    	
    	//Mark topic 1 as complete
    	markComplete();
    }
    
    @Test(priority = 4, dependsOnMethods = "openLesson1")
    public void openLesson1Topic3() {
    	//wait for topic 1 page to load
    	wait.until(ExpectedConditions.titleContains("Basic Investment & Social Media Influencing"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "Basic Investment & Social Media Influencing – Alchemy LMS");
    	
    	//Mark topic 1 as complete
    	markComplete();
    	
    	//Wait for lesson page to load again
    	wait.until(ExpectedConditions.titleContains("Developing Strategy"));
    	
    	//Mark the first lesson as complete
    	markComplete();
    }
    
    @Test(priority = 5, dependsOnMethods = "openCourse")
    public void openLesson2() {
    	//wait for second lesson to load
    	wait.until(ExpectedConditions.titleContains("Monitoring & Digital Advertising"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "Monitoring & Digital Advertising – Alchemy LMS");
    	
    	//Open first topic
    	driver.findElement(By.xpath("//div[@id = 'ld-table-list-item-200']")).click();
    }
    
    @Test(priority = 6, dependsOnMethods = "openLesson2")
    public void openLesson2Topic1() {
    	//wait for topic 1 page to load
    	wait.until(ExpectedConditions.titleContains("Success with Advert"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "Success with Advert – Alchemy LMS");
    	
    	//Mark topic 1 as complete
    	markComplete();
    }
    
    @Test(priority = 7, dependsOnMethods = "openLesson2")
    public void openLesson2Topic2() {
    	//wait for topic 2 page to load
    	wait.until(ExpectedConditions.titleContains("Relationships"));
    	
    	//Assertion
    	Assert.assertEquals(driver.getTitle(), "Relationships – Alchemy LMS");
    	
    	//Mark topic 2 as complete
    	markComplete();
    	
    	//Wait for lesson page to load again
    	wait.until(ExpectedConditions.titleContains("Monitoring & Digital Advertising"));
    	
    	//Mark the second lesson as complete
    	markComplete();
    }
    
    @Test(priority = 8)
    public void checkCourseProgress() {
    	//wait for third topic page to load
    	wait.until(ExpectedConditions.titleContains("Investment & Marketing Final Strategies"));
    	
    	//Click Back to Course
    	driver.findElement(By.xpath("//a[contains(text(), 'Back to Course')]")).click();
    	
    	//wait for page to load
    	wait.until(ExpectedConditions.titleContains("Social Media Marketing"));
    	
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