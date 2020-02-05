package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CRM1 {
@Test
public void crm1() {
	WebDriver driver=new ChromeDriver();
	driver.get("http://alchemy.hguy.co/crm");
	String expected="SuiteCRM";
	String actual=driver.getTitle();
	Assert.assertEquals(actual, expected);
	driver.close();
}
}
