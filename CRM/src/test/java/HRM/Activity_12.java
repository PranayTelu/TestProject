package HRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class Activity_12 {
    public static void main(String[] args) throws IOException, CsvException {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Navigate to login page
        driver.get("http://alchemy.hguy.co/orangehrm/symfony/web/index.php/auth/login");

        //Login in to CRM
        driver.findElement(By.id("txtUsername")).sendKeys("orange");
        driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
        driver.findElement(By.id("btnLogin")).click();

        // Navigate to add new Employee page
        WebElement PIM = driver.findElement(By.id("menu_pim_viewPimModule"));
        PIM.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAdd"))).click();

        // Load CSV file
        String workingDir = System.getProperty("user.dir");
        String filepath = workingDir + "/sample.csv";
        CSVReader reader = new CSVReader(new FileReader(filepath));

        // Load content into list
        List<String[]> list = reader.readAll();
        System.out.println("Total number of rows are: " + list.size());

        // Create Iterator reference
        Iterator<String[]> itr = list.iterator();

        // Iterate all values
        while(itr.hasNext()) {
            String[] str = itr.next();

            // Enter First Name and Last Name
            driver.findElement(By.id("firstName")).sendKeys(str[0]);
            driver.findElement(By.id("lastName")).sendKeys(str[1]);

            // Check if create login is checked
            if ( !driver.findElement(By.id("chkLogin")).isSelected() )
            {
                driver.findElement(By.id("chkLogin")).click();
            }

            // Enter Username and Password 
            driver.findElement(By.id("user_name")).sendKeys(str[2]);
            driver.findElement(By.id("user_password")).sendKeys(str[3]);
            driver.findElement(By.id("re_password")).sendKeys(str[3]);
            driver.findElement(By.id("btnSave")).click();

            // Click on Add New User button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_addEmployee"))).click();
        }

        reader.close();

        // Go to Employee List page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewEmployeeList"))).click();
        itr = list.iterator();

        // Re-iterate all the values to verify if user exists
        while(itr.hasNext()){
            String[] str = itr.next();
            driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys(str[0]);
            driver.findElement((By.id("searchBtn"))).click();

            WebElement rows = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));
            List<WebElement> cols = rows.findElements(By.tagName("td"));

            if(cols.size() > 1 ){
                System.out.println("User: "+str[0]+" exists");
            }
            else {
                System.out.println("User: " + str[0] + " does not exists");
            }

            driver.findElement(By.id("resetBtn")).click();
        }

        driver.close();
    }
}