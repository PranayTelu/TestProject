package HRM;
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

    @BeforeTest()
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser and navigate to http://alchemy.hguy.co/orangehrm
        driver.get("http://alchemy.hguy.co/orangehrm");

        //Maximize the browser
        driver.manage().window().maximize();
    }

    @Test()
    public void Test(){
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

        //Find the PIM menu item and click it
        driver.findElement(By.cssSelector("#menu_pim_viewPimModule")).click();

        //Find the "Add" button and click it
        driver.findElement(By.cssSelector("#btnAdd")).click();

        //Make sure you're on the "Add Employee" page
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Add Employee");

        //Fill in the First Name
        driver.findElement(By.cssSelector("#firstName")).sendKeys("John");

        //Fill in the Last Name
        driver.findElement(By.cssSelector("#lastName")).sendKeys("Smith");

        //Click the Save Button
        driver.findElement(By.cssSelector("#btnSave")).click();

        //Verify that the user got created
        Assert.assertEquals(driver.findElement(By.cssSelector("#profile-pic>h1")).getText(),"John Smith");
    }

    @AfterTest()
    public void afterTest(){
        driver.close();
    }
}