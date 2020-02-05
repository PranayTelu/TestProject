package HRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_3 {
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

        //Find the Homepage Heading
        WebElement heading = driver.findElement(By.tagName("h1"));

        //Make sure the heading reads "Dashboard"
        Assert.assertEquals(heading.getText(), "Dashboard");
    }

    @AfterTest()
    public void afterTest(){
        driver.close();
    }
}
