package lms;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Activity_4 {
	WebDriver driver;
	
    @BeforeTest
    public void beforeTest() {
    	driver = new FirefoxDriver();
    	driver.get("https://alchemy.hguy.co/lms");
    }
    
    @Test
    public void popularCourseTest() {
    	WebElement courseCard = driver.findElement(By.cssSelector(".ld_course_grid:nth-child(2) > article > div.caption > h3.entry-title"));
    	String secondCourseCardTitle = courseCard.getText();
    	
    	Assert.assertEquals(secondCourseCardTitle, "Email Marketing Strategies");
    }
    
    @AfterTest
    public void afterTest() {
    	driver.close();
    }
}