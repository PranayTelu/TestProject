package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CRM7 {

	@Test
	public void CRM() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://alchemy.hguy.co/crm");
		driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@id=\"username_password\"]")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//*[@id=\"bigbutton\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"grouptab_0\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"moduleTab_9_Leads\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"adspan_gbdc4ebf-94c1s3486-d41b-5e1d6f74ca91\"]")).click();
		WebElement phoneNumber=driver.findElement(By.xpath("//span[@class='phone']"));
		System.out.println(phoneNumber.getText());
		

		driver.close();
	}

}
