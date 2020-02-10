package Practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Jobs11 {
	private WebDriver driver;

	@BeforeTest
	public void before() {
		driver = new ChromeDriver();
	}

	@Test
	public void activity10() throws InvalidFormatException, IOException {
		File f=new File("C:\\Users\\Srimaan\\git\\TestProject\\CRM\\src\\Book1.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(f);
		XSSFSheet s=wb.getSheetAt(0);
		Iterator<Row> rowIterator = s.iterator();
        while (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
             
            while (cellIterator.hasNext()) 
            {
            	Cell cell = cellIterator.next();
            	String y=cell.toString();
			

		driver.get("https://alchemy.hguy.co/jobs/wp-admin");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[contains(@id,'user_login')]")).sendKeys("root");
		driver.findElement(By.xpath("//input[contains(@id,'user_pass')]")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[contains(@id,'wp-submit')]")).click();
		driver.findElement(By.xpath("//div[@class='wp-menu-name'][contains(.,'Users')]")).click();
//		driver.findElement(
//				By.xpath("//a[@href='https://alchemy.hguy.co/jobs/wp-admin/user-new.php'][contains(.,'Add New')]"))
//				.click();
//		driver.findElement(By.xpath("//input[contains(@id,'user_login')]")).sendKeys(cell.toString());
//		cell = cellIterator.next();
//		driver.findElement(By.xpath("//input[contains(@id,'email')]")).sendKeys(cell.toString());
//		driver.findElement(By.xpath("//button[@type='button'][contains(.,'Show password')]")).click();
//		driver.findElement(By.xpath("//input[contains(@id,'pass1-text')]")).clear();
//		cell = cellIterator.next();
//		driver.findElement(By.xpath("//input[contains(@id,'pass1-text')]")).sendKeys(cell.toString());
//		driver.findElement(By.xpath("//input[contains(@id,'createusersub')]")).click();
		driver.findElement(By.xpath("//input[contains(@id,'user-search-input')]")).sendKeys(y);
		driver.findElement(By.xpath("//input[contains(@id,'search-submit')]")).click();
		WebElement li= driver.findElement(By.xpath("//td[@class='username column-username has-row-actions column-primary']/strong[contains(.,"+y+")]"));
		
		
			String actual=li.getText();
			Assert.assertEquals(actual, y);
//			Cell cell1=row.createCell(4);
//			cell1.setCellValue("Passed");
//			FileOutputStream fos=new FileOutputStream("C:\\Users\\Srimaan\\git\\TestProject\\CRM\\src\\Book1.xlsx");
//			
//			wb.write(fos);
	break;
            }
		}
		

	}

	@AfterTest
	public void close() {
		driver.close();
	}
}
