package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_5 {
    private WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

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
        // Get the navbar element
        WebElement navbar = driver.findElement(By.cssSelector(".navbar"));

        // Print the navbar's color
        System.out.println(navbar.getCssValue("background-color"));
    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}