package Practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class allCourses {
	
	private WebDriver driver;
	@BeforeTest
	public void driverRunner() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void allcourses() {
		
		driver.get("https://alchemy.hguy.co/lms");
		driver.findElement(By.xpath("//span[@class='uagb-inline-editing '][contains(.,'View Courses')]")).click();
		List<WebElement>li=driver.findElements(By.tagName("h3"));
		for (WebElement webElement : li) {
			System.out.println(webElement.getText());
		}
	}
	
	@AfterTest
	public void driverClosing() {
		driver.close();
	}

}
