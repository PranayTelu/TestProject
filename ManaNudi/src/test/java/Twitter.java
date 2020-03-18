import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Twitter {
	@Test
	public void TwitterRT() throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://twitter.com/");
		// Click on signup
		driver.findElement(By.xpath("//div[@dir='auto'][contains(.,'Sign up')]")).click();

		// Enter Name
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Ram");

		// Email
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://temp-mail.org/en/");
		driver.findElement(By.xpath("//button[contains(@class,'no-ajaxy tm-btn btn-gray click-to-copy')]")).click();
		driver.switchTo().window(tabs.get(0));
		// Switch to mail
		driver.findElement(By.xpath(
				"//span[@class='css-901oao css-16my406 r-1qd0xha r-ad9z0x r-bcqeeo r-qvutc0'][contains(.,'Use email instead')]"))
				.click();
		// Paste Mail
		driver.findElement(By.xpath("//input[contains(@autocomplete,'email')]"))
				.sendKeys(Keys.chord(Keys.CONTROL, "v"));

		// Click on Next
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(.,'Next')])[2]")).click();
		driver.findElement(By.xpath("(//span[contains(.,'Next')])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(.,'Sign up')])[2]")).click();

		// Getting Code
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.xpath("(//a[@class='viewLink title-subject'])[2]")).click();
		String verify = driver.findElement(By.xpath("//td[contains(@class,'h1 black')]")).getText();

		// Pasting the code
		driver.switchTo().window(tabs.get(0));
		driver.findElement(By.xpath("//input[@name='verfication_code']")).sendKeys(verify);

		// Clicking on next
		Thread.sleep(100);
		driver.findElement(By.xpath("(//span[contains(.,'Next')])[2]")).click();

		// Entering Password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("System@123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[contains(.,'Next')])[2]")).click();

	}

}
