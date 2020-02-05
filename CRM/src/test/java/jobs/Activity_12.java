package jobs;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Activity_12 {
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
    public void openPostJobsPageAndLogin() {
    	//Click the Job menu item
    	driver.findElement(By.linkText("Post a Job")).click();
    	
    	//wait for page to load
    	wait.until(ExpectedConditions.titleIs("Post a Job – Alchemy Jobs"));

        //Click on the "Sign in" button
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/main/article/div/form/fieldset[1]/div/a")).click();

        //Wait until the log in page loads
        wait.until(ExpectedConditions.urlContains("wp-login"));

        //Verify that you have moved to the sign in page
        Assert.assertEquals(driver.getCurrentUrl(), "https://alchemy.hguy.co/jobs/wp-login.php?redirect_to=https%3A%2F%2Falchemy.hguy.co%2Fjobs%2Fpost-a-job%2F");

        //Fill in the credentials shared
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");

        //Click on the "Log In" button
        driver.findElement(By.id("wp-submit")).click();

        //Wait until the page redirects back
        wait.until(ExpectedConditions.urlContains("post-a-job"));
        
        //Assertion
    	String pageTitle = driver.findElement(By.className("entry-title")).getText();
    	Assert.assertEquals(pageTitle, "Post a Job");
    }
    
    @Test(dependsOnMethods = "openPostJobsPageAndLogin")
    public void fillDataFromExcel() {
    	String filePath = "src/test/resources/job_listings.xlsx";
    	List<List<String>> data = readJobListingsExcel(filePath);
    	
    	List<String> filledDetails = data.get(1);
    	
    	//Fill up the details of the job posting
        //Enter Job Title
        driver.findElement(By.id("job_title")).sendKeys(filledDetails.get(0));

        //Enter Job Location
        driver.findElement(By.id("job_location")).sendKeys(filledDetails.get(1));

        //Enter Job Type
        Select job_type = new Select(driver.findElement(By.id("job_type")));
        job_type.selectByVisibleText(filledDetails.get(2));

        //Enter into the "Description" iframe to enter the description
        driver.switchTo().frame(0);

        //Enter the description
        driver.findElement(By.cssSelector("#tinymce")).sendKeys(filledDetails.get(3));

        //Leave the iframe and go back to default window
        driver.switchTo().defaultContent();

        //Clear the company name field
        driver.findElement(By.id("company_name")).clear();

        //Enter the company name
        driver.findElement(By.id("company_name")).sendKeys(filledDetails.get(4));

        //Click the "Preview" button
        driver.findElement(By.cssSelector("input.button:nth-child(4)")).click();

        //Wait for the page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job_listing_preview")));

        //Verify that the heading matches your job title
        Assert.assertEquals(driver.findElement(By.cssSelector(".job_listing_preview > h1:nth-child(1)")).getText(), "Senior Tech Manager");

        //Click on the "Submit Listing" button
        driver.findElement(By.id("job_preview_submit_button")).click();
    }
    
    public List<List<String>> readJobListingsExcel(String filePath) {
    	List<List<String>> data = new ArrayList<List<String>>();
    	try {
    	    FileInputStream file = new FileInputStream(filePath);

    	    //Create Workbook instance holding reference to Excel file
    	    XSSFWorkbook workbook = new XSSFWorkbook(file);

    	    //Get first sheet from the workbook
    	    XSSFSheet sheet = workbook.getSheetAt(0);

    	    //Iterate through each rows one by one
    	    Iterator<Row> rowIterator = sheet.iterator();    	    
    	    while(rowIterator.hasNext()) {
    	    	List<String> rowData = new ArrayList<String>();
    	        Row row = rowIterator.next();
    	        //For each row, iterate through all the columns
    	        Iterator<Cell> cellIterator = row.cellIterator();

    	        while (cellIterator.hasNext()) {
    	            Cell cell = cellIterator.next();
    	            if(row.getLastCellNum() == 5) {
    	            	rowData.add(cell.getStringCellValue());
    	            }
    	        }
    	        data.add(rowData);
    	    }
    	    file.close();
    	    workbook.close();
    	}
    	catch (Exception e) {
    	    e.printStackTrace();
    	}
    	return data;
    }

    @AfterClass
    public void afterClass() {
        //Close the browser
        driver.close();
    }
}