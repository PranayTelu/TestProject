package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class Activity_6 {
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
        // Find the navigation bar
        List<WebElement> Navbar = driver.findElements(By.cssSelector("ul.navbar-nav > li"));

        // Iterate through the Navbar to find "Activities"
        for(WebElement MenuItem:Navbar) {
            if (MenuItem.getText() == "ACTIVITIES") {

                // Make sure that "Activities" exists and is clickable
                Assert.assertTrue(MenuItem.isDisplayed());

            }
        }

    }

    @AfterTest
    public void afterTest(){
        // Close the browser
        driver.close();
    }

}