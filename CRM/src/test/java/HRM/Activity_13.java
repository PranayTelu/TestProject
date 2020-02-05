package HRM;


import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_13 {
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
	public void addCandidatesCSV() {
		File file = null;
		try {
			file = new File(Activity_11.class.getClassLoader().getResource("OrangeEmployeeImport.csv").toURI());
		} catch(Exception e) {
			System.out.println(e);
		}
		
        //Find and click the Recruitement menu item
		WebElement dataImportLink = driver.findElement(By.xpath("//a[@id='menu_admin_pimCsvImport']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dataImportLink);
		
		//wait for the page to load
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("head"), "CSV Data Import"));

		//Upload CSV with data
		driver.findElement(By.id("pimCsvImport_csvFile")).sendKeys(file.getAbsolutePath());

		//Click Save
		driver.findElement(By.id("btnSave")).click();
		
		//Click the Employee List menu item
		driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
		
		//wait for table to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultTable")));

		//Get name in result table
		String candidateName = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[4]/td[3]")).getText();
		Assert.assertEquals(candidateName, "Ying Nam");
	}
	
	@AfterClass
	public void tearDown() {
		//close the browser
		driver.close();
	}
}