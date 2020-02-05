package lms;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class Activity_1 {
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.get("https://alchemy.hguy.co/lms");
	}
	
	@Test
	public void siteTitleTest() {
		String siteTitle = driver.getTitle();
		AssertJUnit.assertEquals(siteTitle, "Alchemy LMS – An LMS Application");
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}
}