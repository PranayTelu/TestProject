package CRM;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Activity_13 {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeTest
    public void beforeTest(){
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        // Set WebDriverWait to 10s
        wait = new WebDriverWait(driver, 10);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        // Open the browser and navigate to http://alchemy.hguy.co/crm
        driver.get("http://alchemy.hguy.co/crm");

        // Login into the site
        // Enter Username
        driver.findElement(By.cssSelector("#user_name")).sendKeys("admin");

        // Enter Password
        driver.findElement(By.cssSelector("#username_password")).sendKeys("pa$$w0rd");

        // Click the login button
        driver.findElement(By.cssSelector("#bigbutton")).click();

        // Wait until the dashlets load
        Actions action = new Actions(driver);

        WebElement all = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grouptab_5']")));
        action.moveToElement(all).click().build().perform();

        // Create an array of web element for all drop-down options
        List<WebElement> optionList = driver.findElements(By.xpath("//*[@id='toolbar']/ul/li[7]/span[2]/ul/li"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", optionList.get(24) );
        optionList.get(24).click();
    }

    @DataProvider(name = "products")
    public Object[][] products() {
        List<List<String>> data = new ArrayList<List<String>>();
        // Path of the file to upload
        String workingDir = System.getProperty("user.dir");
        String filePath = workingDir + "/Products.xlsx";

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
                    String value = cell.toString();
                    rowData.add(value);

                }
                data.add(rowData);
            }
            file.close();
            workbook.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<String> row1 = data.get(1);
        List<String> row2 = data.get(2);
        List<String> row3 = data.get(3);
        List<String> row4 = data.get(4);
        List<String> row5 = data.get(5);

        return new Object[][] {
                {row1.get(0), row1.get(1), row1.get(2), row1.get(3), row1.get(4)},
                {row2.get(0), row2.get(1), row2.get(2), row2.get(3), row2.get(4)},
                {row3.get(0), row3.get(1), row3.get(2), row3.get(3), row3.get(4)},
                {row4.get(0), row4.get(1), row4.get(2), row4.get(3), row4.get(4)},
                {row5.get(0), row5.get(1), row5.get(2), row5.get(3), row5.get(4)}
        };

    }
    @Test (dataProvider = "products")
    public void addProducts(String productName, String productType, String price, String contact, String description) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='actionMenuSidebar']/ul/li[1]"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("SAVE")));
        driver.findElement(By.id("name")).sendKeys(productName);
        Select prodcutTypeDropDown = new Select(driver.findElement(By.id("type")));
        prodcutTypeDropDown.selectByValue(productType);
        driver.findElement(By.id("price")).sendKeys(price);
        driver.findElement(By.id("contact")).sendKeys(contact);
        WebElement dropDown = driver.findElement(By.xpath("//*[@id=\"EditView_contact_results\"]/div"));
        wait.until(ExpectedConditions.visibilityOf(dropDown));
        dropDown.click();
        driver.findElement(By.id("description")).sendKeys(description);

        driver.findElement(By.id("SAVE")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='pagecontent']/div[1]/h2")).getText().toLowerCase(), productName.toLowerCase());

    }

    @Test
    public void verifyProducts()  {
        driver.findElement(By.xpath("//*[@id='actionMenuSidebar']/ul/li[2]/a/div[2]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='MassUpdate']/div[3]/table/tbody/tr[1]")));
        List rows = driver.findElements(By.xpath("//*[@id='MassUpdate']/div[3]/table/tbody/tr"));
        Assert.assertEquals(rows.size(), 5);
    }

    @AfterTest
    public void afterTest(){
        driver.close();
    }

}
