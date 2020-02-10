package Practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Jobs15 {

	@Test
	public void newww() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com");
		List<WebElement>list=driver.findElements(By.xpath("//a"));
		for (WebElement webElement : list) {
			System.out.println(webElement.getText());
			
		}
		List<WebElement>li=driver.findElements(By.xpath("//a[@href]"));
		for (WebElement webElement : li) {
			System.out.println(webElement.getText());
			
		}
		driver.close();
	}
	
}
