package HRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_5 {
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

        //Find the "My Info" menu item and click it
        driver.findElement(By.cssSelector("#menu_pim_viewMyDetails")).click();

        //Verify that you're on the right page
        Assert.assertEquals(driver.findElement(By.cssSelector(".head>h1")).getText(), "Personal Details");

        //Click on the "Edit" button
        driver.findElement(By.cssSelector("#btnSave")).click();

        //Clear the "Last Name" field
        driver.findElement(By.cssSelector("#personal_txtEmpLastName")).clear();

        //Change Last Name to "Doe"
        driver.findElement(By.cssSelector("#personal_txtEmpLastName")).sendKeys("Doe");

        //Find the Nationality Drop-down
        Select Nationality = new Select(driver.findElement(By.cssSelector("#personal_cmbNation")));

        //Change Nationality to Japanese
        Nationality.selectByVisibleText("Japanese");

        //Click on the "Save" button
        driver.findElement(By.cssSelector("#btnSave")).click();
    }

    @AfterTest()
    public void afterTest(){
        driver.close();
    }
}