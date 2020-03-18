package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class manaNudiManaNadi {
	
	WebDriver driver = new FirefoxDriver();

	@Test
	@Given("^the homepage of website$")
	public void the_homepage_of_website() {
		// Write code here that turns the phrase above into concrete actions
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.mananudimananadi.org/");

	}

	@When("^Enterded the data$")
	public void enterded_the_data() throws InterruptedException {
		
		driver.findElement(By.xpath("//span[contains(.,'నేను సైతం')]")).click();
		// Name
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("Pranay");
		// Email
		{

			// chose a Character random from this String
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

			// create StringBuffer size of AlphaNumericString
			StringBuilder sb = new StringBuilder();

			for (int j = 0; j <= 5; j++) {

				// generate a random number between
				// 0 to AlphaNumericString variable length
				int index = (int) (AlphaNumericString.length() * Math.random());

				// add Character one by one in end of sb
				sb.append(AlphaNumericString.charAt(index));
			}

			String s = sb.toString();
			driver.findElement(By.xpath("/html/body/app-root/div[4]/div/app-registration/div/form/div[2]/div/input"))
					.sendKeys(s + "@gmail.com");
		}

		// PhoneNumber
		int num = (int) (100000 * Math.random());
		driver.findElement(By.xpath("//input[contains(@onlynumber,'true')]")).sendKeys("94919" + num);
		// State
		/*
		 * WebElement state = driver.findElement(By.xpath(
		 * "(//button[contains(@class,'ngx-dropdown-button')])[1]")); Select select =
		 * new Select(state); select.selectByValue("తెలంగాణ ")
		 */
		Thread.sleep(500);
		driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//li[@tabindex='-1'])[2]")).click();
		// District
		driver.findElement(By.xpath("//span[contains(.,'మీ జిల్లాను ఎంచుకోండి')]")).click();
		driver.findElement(By.xpath("//li[@tabindex='-1'][contains(.,'నిజామాబాదు')]")).click();
		// Constituency
		driver.findElement(By.xpath("//span[contains(.,'మీ నియోజకవర్గాన్ని ఎంచుకోండి')]")).click();
		driver.findElement(By.xpath("//li[@tabindex='-1'][contains(.,'బాల్కొండ')]")).click();
		// Captcha
		String s = driver.findElement(By.xpath("//*[@id=\"support_form\"]/form/div[8]/div/div/div/label")).getText()
				.trim();
		String removespace = s.replaceAll("\\s+", "");
		String[] parts = removespace.split("\\+");
		String part1 = parts[0];
		String part2 = parts[1];
		String[] parts1 = part2.split("\\=");
		String part11 = parts1[0];

		// sum two numbers
		int summation = Integer.parseInt(part1) + Integer.parseInt(part11);

		// Math Capcha value
		WebElement capta = driver.findElement(By.xpath("//input[contains(@id,'captcha')]"));
		capta.clear();
		capta.sendKeys("" + summation);
		// driver.findElement(By.xpath("//input[contains(@formcontrolname,'name')]")).sendKeys("Pranay");

	}

	@Then("^click on register$")
	public void click_on_register() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-block btn-lg'][contains(.,'నేను సైతం')]"))
				.click();

	}

	@Then("^refresh page for process repetion$")
	public void refresh_page_for_process_repetion() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
	
	}

	@Then("^Close the browser$")
	public void close_the_browser() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		String n=driver.findElement(By.xpath("//*[@id=\"pk__msg\"]/div[1]/div/span[2]")).getText();
		System.out.println(n);
		driver.quit();

	}

}
