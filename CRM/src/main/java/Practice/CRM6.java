package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CRM6 {
	@Test
	public void CRM() {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://alchemy.hguy.co/crm");
	driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys("admin");
	driver.findElement(By.xpath("//*[@id=\"username_password\"]")).sendKeys("pa$$w0rd");
	driver.findElement(By.xpath("//*[@id=\"bigbutton\"]")).click();
	WebElement e=driver.findElement(By.xpath("//a[@href='#'][contains(@id,'3')][contains(.,'Activities')]"));
	System.out.println(e.isDisplayed());
	
	driver.close();
}
}
