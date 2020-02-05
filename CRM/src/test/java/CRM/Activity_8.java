package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Activity_8 {
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
        /* Build an action to click on the "Accounts" sub-menu item in the "Sales" menu item
         * First, we will declare a new instance of Actions. This will allow us to create a new Action.
         * Next, we create an action called "GoToAccounts" which moves the mouse to the "Sales" menu item and then the "Accounts" sub-menu" item.
         * Finally, we perform the action we just created.
         */
        Actions builder = new Actions(driver);
        Action GoToAccounts = builder.moveToElement(driver.findElement(By.cssSelector("#grouptab_0"))).moveToElement(driver.findElement(By.cssSelector("li.topnav:nth-child(2) > span:nth-child(2) > ul:nth-child(3) > li:nth-child(2) > a:nth-child(1)"))).click().build();
        GoToAccounts.perform();

        // Wait until the table loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list")));

        // Get all the rows of the table
        List<WebElement> Rows = driver.findElements(By.xpath("//table[@class='list view table-responsive']/tbody/tr"));

        // Print the "Name" column of the first 5 odd rows
        for (int i=0; i<=10; i++) {
            if ( i % 2 != 0 ) {
                for (int j = 3; j <= 3; j++) {
                    System.out.println(driver.findElement(By.xpath("//table[@class='list view table-responsive']/tbody/tr[" + i + "]/td[" + j + "]")).getText());
                }
                System.out.println("");
            }
        }

    }

    @AfterTest
    public void afterTest(){
        // Close the browser
        driver.close();
    }

}