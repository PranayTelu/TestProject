package HRM;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_14 {
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
	public void addingVacancies() {
		String filePath = "src/test/resources/job_vacancies.xlsx";
		List<List<String>> data = readJobVacanciesExcel(filePath);
		List<String> formDetails = data.get(1);
		
		//Find and click Vacancies menu item
		WebElement vacancyLink = driver.findElement(By.id("menu_recruitment_viewJobVacancy")); 
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", vacancyLink);
		
		//Wait for page to load
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("head"), "Vacancies"));
		
		//Click the Add button
		driver.findElement(By.id("btnAdd")).click();
		
		//Wait for page to load
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("head"), "Add Job Vacancy"));
		
		//Fill in the form
		//Select Job Title
		WebElement jobTitleDropdown = driver.findElement(By.id("addJobVacancy_jobTitle"));
		Select jobTitle = new Select(jobTitleDropdown);
		jobTitle.selectByVisibleText(formDetails.get(0));
		
		//Enter Vacancy Name
		driver.findElement(By.id("addJobVacancy_name")).sendKeys(formDetails.get(1));
		//Enter Hiring Manager
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(formDetails.get(2));
		//Enter Number of Positions
		driver.findElement(By.id("addJobVacancy_name")).sendKeys(formDetails.get(3));
		
		//Click Save
		driver.findElement(By.id("btnSave")).click();
		
		//Go back and wait for the page to load
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnBack")));
		driver.findElement(By.id("btnBack")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("head"), "Vacancies"));
		
		//Assert
		String jobTitleText = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[3]")).getText();
		Assert.assertEquals(jobTitleText, "Android Developer");
	}
	
	public List<List<String>> readJobVacanciesExcel(String filePath) {
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
    	            rowData.add(cell.getStringCellValue());
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
	public void tearDown() {
		//close the browser
		driver.close();
	}
}