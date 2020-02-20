package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LMS11 {
	
	@Test
	public void test1() {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alchemy.hguy.co/lms/all-courses/");
		driver.findElement(By.xpath("//img[@src='https://alchemy.hguy.co/lms/wp-content/uploads/2019/05/course-1-free-img-400x223.jpg']")).click();
		
		
		
	}

}
