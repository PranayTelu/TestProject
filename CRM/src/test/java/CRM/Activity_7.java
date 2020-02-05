package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Activity_7 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest(){
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        // Set WebDriverWait to 10s
        wait = new WebDriverWait(driver, 10);

        // Open the browser and navigate to http://alchemy.hguy.co/crm
        driver.get("http://alchemy.hguy.co/crm");

        // Maximize the browser
        driver.manage().window().maximize();

        // Login into the site
        // Enter Username
        driver.findElement(By.cssSelector("#user_name")).sendKeys("admin");

        // Enter Password
        driver.findElement(By.cssSelector("#username_password")).sendKeys("pa$$w0rd");

        // Click the login button
        driver.findElement(By.cssSelector("#bigbutton")).click();
    }

    @Test
    public void Test(){
        /* Build an action to click on the "Leads" sub-menu item in the "Sales" menu item
         * First, we will declare a new instance of Actions. This will allow us to create a new Action.
         * Next, we create an action called "GoToLeads" which moves the mouse to the "Sales" menu item and then the "Leads" sub-menu" item.
         * Finally, we perform the action we just created.
         */
        Actions builder = new Actions(driver);
        Action GoToLeads = builder.moveToElement(driver.findElement(By.cssSelector("#grouptab_0"))).moveToElement(driver.findElement(By.cssSelector("li.topnav:nth-child(2) > span:nth-child(2) > ul:nth-child(3) > li:nth-child(5) > a:nth-child(1)"))).click().build();
        GoToLeads.perform();

        // Wait until the table loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list")));

        // Click the "Additional Details" button of the first result in the table
        driver.findElement(By.xpath("//table[@class='list view table-responsive']/tbody/tr[1]/td[10]")).click();

        // Wait until the details popup opens
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ui-dialog:nth-child(9)")));

        // Print the mobile phone number on the popup
        System.out.println(driver.findElement(By.cssSelector("span.phone:nth-child(8)")).getText());

        // Make sure the phone number is correct
        Assert.assertEquals(driver.findElement(By.cssSelector("span.phone:nth-child(8)")).getText(), "(521) 091-0136");
    }

    @AfterTest
    public void afterTest(){
        // Close the browser
        driver.close();
    }

}