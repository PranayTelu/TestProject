package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CRM9 {
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
		driver.findElement(By.xpath("(//a[contains(@id,'moduleTab_9_Accounts')])[1]")).click();
		
		   for (int i=0; i<=10; i++) {
	            
	                for (int j = 3; j <= 3; j++) {
	                    System.out.println(driver.findElement(By.xpath("//table[@class='list view table-responsive']/tbody/tr[" + i + "]/td[" + j + "]")).getText());
	                    System.out.print("--");
	                    System.out.print(driver.findElement(By.xpath("//*[@id=\"MassUpdate\"]/div[3]/table/tbody/tr[1]/td[7]")).getText());
	                }
	                
	            
	        }
		driver.close();
	}
}
