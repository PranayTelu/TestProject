package jobs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_10 {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeTest
	public void beforeTest() {
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();

		// Setup the wait time
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Open the browser and navigate to http://alchemy.hguy.co/jobs/wp-admin
		driver.get("http://alchemy.hguy.co/jobs/wp-admin");

		// Maximize the browser
		driver.manage().window().maximize();

		// Fill in the credentials shared
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");

		// Click on the "Log In" button
		driver.findElement(By.id("wp-submit")).click();
	}

	@Test
	public void Test() throws InterruptedException {

		// Verify that you're on the admin page
		Assert.assertEquals(driver.findElement(By.cssSelector(".wrap > h1:nth-child(1)")).getText(), "Dashboard");

		// Click on the "Users" menu item
		driver.findElement(By.cssSelector("a.menu-icon-users")).click();

		// Click on the "Add New" button
		driver.findElement(By.cssSelector("#menu-users > ul:nth-child(2) > li:nth-child(3) > a:nth-child(1)")).click();

		// Fill in the user details
		// Set the username
		driver.findElement(By.id("user_login")).sendKeys("hagrid12");

		// Set the email. Make sure you use your own emails here.
		driver.findElement(By.id("email")).sendKeys("example123@cklabs.com");

		// Set the first name
		driver.findElement(By.id("first_name")).sendKeys("Rubeus12");

		// Set the last name
		driver.findElement(By.id("last_name")).sendKeys("Hagrid123");

		driver.findElement(By.xpath("//button[@type='button'][contains(.,'Show password')]")).click();
		Thread.sleep(500);

		driver.findElement(By.xpath("//input[@id='pass1-text']")).clear();
		String theText = "asdiffoiasndfoiasdf";
		WebElement fieldElement =driver.findElement(By.xpath("//input[@id='pass1-text']"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + theText + "';", fieldElement);
		Thread.sleep(500);

		// Click the Add button
		driver.findElement(By.id("createusersub")).click();

	}

	@AfterTest
	public void afterTest() {
		// Close the browser
		driver.close();
	}

}