package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WeekendChallenge2 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Open browser
		driver.get("https://www.google.com");
		
		//Find search field and type 'Cheese'
		driver.findElement(By.name("q")).sendKeys("Cheese");
		
		//Find and click the fourth suggestion
		WebElement fourthSuggestion = driver.findElement(By.xpath("//span[contains(.,'cheese dosa')]"));
		
		//Wait for the button to be clicked
		wait.until(ExpectedConditions.elementToBeClickable(fourthSuggestion));
		fourthSuggestion.click();
		
		//Wait for results to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("result-stats")));
		
		//Find and print the number of results
		String results = driver.findElement(By.id("result-stats")).getText();
		System.out.println("Number of resutls: " + results);
		
		//Find and click the first result link
		WebElement thirdLink = driver.findElement(By.xpath("//div[@class='srg']/div[@class='g']//div[@class='r']/a"));
		thirdLink.click();
		
		//Wait for new page to load
		wait.until(ExpectedConditions.titleContains("Wikipedia"));
		
		//Print page title
		System.out.println("New page title is: " + driver.getTitle());
		
		//Close browser
		driver.close();

	}

}
