package HRM;


import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_11 {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void setUp() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		
		//Open the browser
		driver.get("http://alchemy.hguy.co:8080/orangehrm/");
	}

	@Test
	public void HRMLogin() {
        //Find the username field
        driver.findElement(By.cssSelector("#txtUsername")).sendKeys("orange");

        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("orangepassword123");

        //Find the login button
        driver.findElement(By.cssSelector("#btnLogin")).click();

        //wait for page to load
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("head"), "Dashboard"));

        //Find the Homepage Heading
        WebElement heading = driver.findElement(By.tagName("h1"));

        //Make sure the heading reads "Dashboard"
        Assert.assertEquals(heading.getText(), "Dashboard");
	}
	
	@Test(dependsOnMethods = "HRMLogin")
	public void addCandidate() {
		File file = null;
		try {
			file = new File(Activity_11.class.getClassLoader().getResource("test_resume.docx").toURI());
		} catch(Exception e) {
			System.out.println(e);
		}
		//wait for page to load
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='menu_recruitment_viewRecruitmentModule']")));
		
        //Find and click the Recruitement menu item
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewRecruitmentModule']")).click();
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewRecruitmentModule']")).click();
		
		//wait for the page to load
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("head"), "Candidates"));
		
		//Find and click the Add button
		driver.findElement(By.id("btnAdd")).click();
		
		//wait for the page to load
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("head"), "Add Candidate"));
		
		//Fill in the form
		//Enter full name
		driver.findElement(By.id("addCandidate_firstName")).sendKeys("Satnam");
		driver.findElement(By.id("addCandidate_lastName")).sendKeys("Kuri");
		//Enter email
		driver.findElement(By.id("addCandidate_email")).sendKeys("satishkuri@yopmail.com");
		//Upload resume
		driver.findElement(By.id("addCandidate_resume")).sendKeys(file.getAbsolutePath());
		
		//Click Save
		driver.findElement(By.id("btnSave")).click();
		
		//wait for Back button to show and click it
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btnBack")));
		driver.findElement(By.id("btnBack")).click();
		
		//wait for table to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultTable")));
		
		//Get name in result table
		String candidateName = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[3]")).getText();
		Assert.assertEquals(candidateName, "Satnam Kuri");
	}
	
	@AfterClass
	public void tearDown() {
		//close the browser
		driver.close();
	}
}