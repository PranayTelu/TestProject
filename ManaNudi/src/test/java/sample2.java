import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class sample2 {
	@Test
	public void youtubeTest() throws InterruptedException {
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/watch?v=5FUdrBq-WFo");
		//Click On Like
		driver.findElement(By.xpath("//*[@id=\"top-level-buttons\"]/ytd-toggle-button-renderer[1]/a")).click();
		//Click On sign in popup
		driver.findElement(By.xpath("//yt-formatted-string[@class='style-scope ytd-button-renderer style-blue-text size-default'][contains(.,'Sign in')]")).click();
		//Click on Create a new account
		driver.findElement(By.xpath("//span[@class='NlWrkb snByac'][contains(.,'Create account')]")).click();
		//Click on Myself
		//driver.findElement(By.xpath("//div[@class='jO7h3c']")).click();
		Thread.sleep(200);
		driver.findElement(By.className("jO7h3c")).click();
		
		//Filling the form
		//FirstName
		driver.findElement(By.xpath("//input[contains(@aria-label,'First name')]")).sendKeys("Pranay");
		//LastName
		driver.findElement(By.xpath("//input[contains(@aria-label,'Last name')]")).sendKeys("Telu");
		//Email
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://temp-mail.org/en/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(@class,'no-ajaxy tm-btn btn-gray click-to-copy')]")).click();
		driver.switchTo().window(tabs.get(0));
		
		driver.findElement(By.xpath("//input[@name='Username']")).sendKeys(Keys.chord(Keys.CONTROL,"v"));
		//Password
		driver.findElement(By.xpath("//input[contains(@aria-label,'Password')]")).sendKeys("Hello@123");
		//ConfirmPassword
		driver.findElement(By.xpath("//input[contains(@aria-label,'Confirm')]")).sendKeys("Hello@123");
		//Click on Next
		driver.findElement(By.xpath("//span[@class='RveJvd snByac'][contains(.,'Next')]")).click();
		
		//Getting Verification code
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.xpath("(//a[contains(.,'Verify your email address')])")).click();
		String verify=driver.findElement(By.xpath("//div[@style='font-size:24px;padding-top:20px;padding-bottom:20px;font-weight:bold;text-align:center;']")).getText();
		System.out.println(verify);
		
		//Paste verification code
		driver.switchTo().window(tabs.get(0));
		driver.findElement(By.xpath("//input[contains(@type,'tel')]")).sendKeys(verify);
		
		//Click Verify
		driver.findElement(By.xpath("//span[@class='RveJvd snByac'][contains(.,'Verify')]")).click();
	}

}
