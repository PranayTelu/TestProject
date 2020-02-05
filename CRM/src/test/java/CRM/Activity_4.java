package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_4 {
    private WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser and navigate to http://alchemy.hguy.co/crm
        driver.get("http://alchemy.hguy.co/crm");

        //Maximize the browser
        driver.manage().window().maximize();
    }

    @Test
    public void test() {
        // Find the username field
        WebElement username = driver.findElement(By.cssSelector("#user_name"));

        // Find the password field
        WebElement password = driver.findElement(By.cssSelector("#username_password"));

        // Enter the username into it's field
        username.sendKeys("admin");

        // Enter the password into the password field
        password.sendKeys("pa$$w0rd");

        // Find the login button
        WebElement login_button = driver.findElement(By.cssSelector("#bigbutton"));

        // Click the login button
        login_button.click();

        // Make sure you are logged into the home screen
        Assert.assertTrue(driver.getCurrentUrl().contains("Home"));

    }

    @AfterTest
    public void afterTest() {
        //Close the browser
        driver.close();
    }

}