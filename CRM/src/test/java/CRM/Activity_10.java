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
import java.util.List;

public class Activity_10 {
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
        // Wait until the dashlets load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='dashletTable']")));

        // Store all the dashlets on the page in a list
        List<WebElement> Dashlets = driver.findElements(By.xpath("//table[@class='dashletTable']/tbody/tr/td/ul/li[starts-with(@id, 'dashlet_')]"));

        // Print the size of the list
        System.out.println("Number of Dashlets on this page are: " + Dashlets.size());

        // Make sure there are 6 Dashlets
        Assert.assertEquals(Dashlets.size(), 6);

    }

    @AfterTest
    public void afterTest(){
        // Close the browser
        driver.close();
    }

}