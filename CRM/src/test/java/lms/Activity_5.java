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

public class Activity_5 {
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
    
    @AfterTest
    public void afterTest() {
    	driver.close();
    }
}