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

public class Activity_8 {
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
    public void coutactFormFilling() {
    	//Find menu item and click it
    	WebElement contactLink = driver.findElement(By.linkText("Contact"));
    	contactLink.click();
    	
    	//Wait for page to load
    	wait.until(ExpectedConditions.titleContains("Contact"));
    	
    	//Fill in Full Name
    	WebElement fullNameField= driver.findElement(By.cssSelector("#wpforms-8-field_0"));
    	fullNameField.sendKeys("John Smith");
    	//Fill in Email
    	WebElement emailField= driver.findElement(By.cssSelector("#wpforms-8-field_1"));
    	emailField.sendKeys("johnsmith@example.com");
    	//Fill in Subject
    	WebElement subjectField= driver.findElement(By.cssSelector("#wpforms-8-field_2"));
    	subjectField.sendKeys("Automated Test message");
    	//Fill in Message
    	WebElement messageField= driver.findElement(By.cssSelector("#wpforms-8-field_3"));
    	messageField.sendKeys("This is a message from the automated test.");
    	//Click Submit
    	driver.findElement(By.cssSelector("#wpforms-submit-8")).click();
    	
    	//Wait for confirmation message
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#wpforms-confirmation-8")));
    	
    	//Assertion
    	String expectedString = "Thanks for contacting us! We will be in touch with you shortly.";
    	String actualString = driver.findElement(By.cssSelector("#wpforms-confirmation-8")).getText();
    	Assert.assertEquals(actualString, expectedString);
    }

    @AfterTest
    public void afterTest() {
        driver.close();
    }
}