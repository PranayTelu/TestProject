package lms;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Activity_6 {
	WebDriver driver;
	WebDriverWait wait;
	
    @BeforeTest
    public void beforeTest() {
    	driver = new FirefoxDriver();
    	driver.get("https://alchemy.hguy.co/lms");
    	wait = new WebDriverWait(driver, 10);
    }
    
    @Test
    public void myAccountPageTest() {
    	WebElement myAccountMenu = driver.findElement(By.linkText("My Account"));
    	myAccountMenu.click();
    	
    	wait.until(ExpectedConditions.titleIs("My Account – Alchemy LMS"));
    	Assert.assertEquals(driver.getTitle(), "My Account – Alchemy LMS");
    }
    
    @Test(dependsOnMethods = "myAccountPageTest")
    public void loginTest() {
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
    
    @AfterTest
    public void afterTest() {
    	driver.close();
    }
}