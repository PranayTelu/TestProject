package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CRM3 {
	@Test
	public void CRM() {
		WebDriver driver=new ChromeDriver();
		driver.get("http://alchemy.hguy.co/crm");
		WebElement footer=driver.findElement(By.xpath("/html/body/div[1]/div[3]"));
		System.out.println(footer.getText());
		driver.close();
	}

}
