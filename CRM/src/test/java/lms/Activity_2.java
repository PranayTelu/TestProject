package lms;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Activity_2 {
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.get("https://alchemy.hguy.co/lms");
	}
	
	@Test
	public void headingTest() {
		WebElement siteHeading = driver.findElement(By.className("uagb-ifb-title"));
		String siteHeadingText = siteHeading.getText();
		
		Assert.assertEquals(siteHeadingText, "Learn from Industry Experts");
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}
}
