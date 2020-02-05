package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CRM2 {
	@Test
public void CRM() {
		WebDriver driver=new ChromeDriver();
		driver.get("http://alchemy.hguy.co/crm");
		System.out.println(driver.getCurrentUrl());
		driver.close();
	}

}
