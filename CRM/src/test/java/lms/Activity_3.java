package lms;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Activity_3 {
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.get("https://alchemy.hguy.co/lms");
	}
	
	@Test
	public void finfoboxTitleTest() {
		WebElement infobox = driver.findElement(By.cssSelector("h3.uagb-ifb-title"));
		String infoboxText = infobox.getText();
		
		Assert.assertEquals(infoboxText, "Actionable Training");
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
