package jobs;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class Activity_11 {
    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        
        //Open the browser
        driver.get("https://alchemy.hguy.co/jobs/");
    }

    @Test
    public void openJobsPage() {
    	//Click the Job menu item
    	driver.findElement(By.linkText("Jobs")).click();
    	
    	//wait for page to load
    	wait.until(ExpectedConditions.titleIs("Jobs – Alchemy Jobs"));
    	
    	//Assertion
    	String pageTitle = driver.findElement(By.className("entry-title")).getText();
    	Assert.assertEquals(pageTitle, "Jobs");
    }
    
    @Test(dependsOnMethods = "openJobsPage")
    public void searchJobs() {
    	//Find Keywords field and type name of Job
    	driver.findElement(By.xpath("//input[@id = 'search_keywords']")).sendKeys("Senior", Keys.RETURN);
    	
    	//Filter results
    	List<WebElement> jobTypes = driver.findElements(By.xpath("//ul[@class = 'job_types']/li"));
    	WebElement fullTimeFilter = driver.findElement(By.xpath("//ul[@class = 'job_types']/li[2]"));
    	for(WebElement jobType : jobTypes) {
    		jobType.click();
    	}
    	
    	//Click Full Time filter
    	fullTimeFilter.click();
    	
    	//wait for results to load
    	By jobListingsLocator = By.xpath("//ul[@class = 'job_listings']/li");
    	wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(jobListingsLocator, 1));
    	
    	//Assertion
    	List<WebElement> jobListings = driver.findElements(jobListingsLocator);
    	Assert.assertEquals(jobListings.size(), 2);
    }
    
    @Test(dependsOnMethods = "searchJobs")
    public void applyForJob() {
    	//click the first job listing
    	driver.findElement(By.xpath("//ul[@class = 'job_listings']/li[1]")).click();
    	
    	//wait for page to load
    	wait.until(ExpectedConditions.titleContains("Senior Online Editor"));
    	
    	//Assertion
    	String jobTitle = driver.findElement(By.className("entry-title")).getText();
    	Assert.assertEquals(jobTitle, "Senior Online Editor");
    	
    	//Click Apply
    	driver.findElement(By.xpath("//input[@value = 'Apply for job']")).click();
    	//wait for text to show
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'application_details']/p")));
    	String applyText = driver.findElement(By.xpath("//div[@class = 'application_details']/p")).getText();
    	Reporter.log("Application Details: " + applyText);
    }
    

    @AfterClass
    public void afterClass() {
        //Close the browser
        driver.close();
    }
}