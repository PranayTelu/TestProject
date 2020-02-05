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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Activity_13 {
    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeClass
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Setup the wait time
        wait = new WebDriverWait(driver, 10);

        //Open the browser and navigate to http://alchemy.hguy.co/jobs/wp-admin
        driver.get("http://alchemy.hguy.co/jobs/wp-admin");

        //Maximize the browser
        driver.manage().window().maximize();

        //Fill in the credentials shared
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");

        //Click on the "Log In" button
        driver.findElement(By.id("wp-submit")).click();
    }

    @Test
    public void addNewUser() {
    	String filePath = "src/test/resources/users.xlsx";
    	List<List<String>> data = readUsersExcel(filePath);
    	
    	List<String> userDetails = data.get(1);
    	
    	//Verify that you're on the admin page
        Assert.assertEquals(driver.findElement(By.cssSelector(".wrap > h1:nth-child(1)")).getText(), "Dashboard");

        //Click on the "Users" menu item
        driver.findElement(By.cssSelector("a.menu-icon-users")).click();

        //Click on the "Add New" button
        driver.findElement(By.cssSelector("a.page-title-action")).click();

        //Fill in the user details
        //Set the username
        driver.findElement(By.id("user_login")).sendKeys(userDetails.get(0));

        //Set the email. Make sure you use your own email here.
        driver.findElement(By.id("email")).sendKeys(userDetails.get(1));

        //Set the first name
        driver.findElement(By.id("first_name")).sendKeys(userDetails.get(2));

        //Set the last name
        driver.findElement(By.id("last_name")).sendKeys(userDetails.get(3));
        
        //Set user role
        WebElement userRoleDropdown = driver.findElement(By.id("role"));
        Select userRole = new Select(userRoleDropdown);
        
        userRole.selectByVisibleText(userDetails.get(4));

        //Click the Add button
        driver.findElement(By.id("createusersub")).click();
        
        //wait for page to load
        wait.until(ExpectedConditions.titleContains("Users"));
        
        //Find confirmation message and get text
        String cofirmationText = driver.findElement(By.id("message")).getText();
        //Assert message
        Assert.assertTrue(cofirmationText.contains("New user created"));
    }
    
    public List<List<String>> readUsersExcel(String filePath) {
    	List<List<String>> data = new ArrayList<>();
    	try {
    	    FileInputStream file = new FileInputStream(filePath);

    	    //Create Workbook instance holding reference to Excel file
    	    XSSFWorkbook workbook = new XSSFWorkbook(file);

    	    //Get first sheet from the workbook
    	    XSSFSheet sheet = workbook.getSheetAt(0);

    	    //Iterate through each rows one by one
    	    Iterator<Row> rowIterator = sheet.iterator();    	    
    	    while(rowIterator.hasNext()) {
    	    	List<String> rowData = new ArrayList<>();
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