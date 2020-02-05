package HRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Activity_9 {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeTest()
	public void beforeTest() {

		// Create a new instance of the Firefox driver
		driver = new ChromeDriver();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Initialize the wait
		wait = new WebDriverWait(driver, 10);

		// Open the browser and navigate to http://alchemy.hguy.co/orangehrm
		driver.get("http://alchemy.hguy.co/orangehrm");

		// Maximize the browser
		driver.manage().window().maximize();

		// Find the username field
		WebElement usernameField = driver.findElement(By.cssSelector("#txtUsername"));

		// Enter the username into that field
		usernameField.sendKeys("orange");

		// Find the password field
		WebElement passwordField = driver.findElement(By.cssSelector("#txtPassword"));

		// Enter the password into that field
		passwordField.sendKeys("orangepassword123");

		// Find the login button
		WebElement loginButton = driver.findElement(By.cssSelector("#btnLogin"));

		// Click the login button
		loginButton.click();
	}

	@Test()
	public void Test() {
		// Click the "My Info" link in the navigation menu
		driver.findElement(By.xpath("//*[@id=\"menu_pim_viewMyDetails\"]")).click();

		// Wait for the sidebar to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sidenav")));

		// Click on "Emergency Contacts" in the sidebar
		driver.findElement(By.cssSelector("#sidenav > li:nth-child(3) > a:nth-child(1)")).click();

		// Print the contact names in the tables
		List<WebElement> contactsList = driver.findElements(By.className("emgContactName"));

		// Print all the names in that list
		for (WebElement contact : contactsList) {
			System.out.println(contact.getText());
		}

	}

	@AfterTest()
	public void afterTest() {
		driver.close();
	}
}