package HRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_6 {
    private WebDriver driver;

    @BeforeTest()
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser and navigate to http://alchemy.hguy.co/orangehrm
        driver.get("http://alchemy.hguy.co/orangehrm");

        //Maximize the browser
        driver.manage().window().maximize();

        //Find the username field
        WebElement usernameField = driver.findElement(By.cssSelector("#txtUsername"));

        //Enter the username into that field
        usernameField.sendKeys("orange");

        //Find the password field
        WebElement passwordField = driver.findElement(By.cssSelector("#txtPassword"));

        //Enter the password into that field
        passwordField.sendKeys("orangepassword123");

        //Find the login button
        WebElement loginButton = driver.findElement(By.cssSelector("#btnLogin"));

        //Click the login button
        loginButton.click();
    }

    @Test()
    public void Test(){

        //Look for the Dashboard nav element
        WebElement navBar = driver.findElement(By.cssSelector("#menu_dashboard_index"));

        //Verify that it is visible
        Assert.assertTrue(navBar.isDisplayed());

        //Verify that it is clickable
        Assert.assertTrue(navBar.isEnabled());

    }

    @AfterTest()
    public void afterTest(){
        driver.close();
    }
}