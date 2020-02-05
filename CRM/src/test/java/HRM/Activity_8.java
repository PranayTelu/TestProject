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

public class Activity_8 {
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
        //Click the "Leave" button in the navigation bar
        driver.findElement(By.cssSelector("#menu_leave_viewLeaveModule")).click();

        //Click the "Apply" button in the sub-navigation bar
        driver.findElement(By.cssSelector("#menu_leave_applyLeave")).click();

        //Verify that you're on the right page
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Apply Leave");

        //Get the "Leave Type" drop down
        Select leave_type = new Select(driver.findElement(By.cssSelector("#applyleave_txtLeaveType")));

        //Set leave type to "Paid Leave"
        leave_type.selectByVisibleText("Paid Leave");

        //Clear the default text in the "From Date" field
        driver.findElement(By.cssSelector("#applyleave_txtFromDate")).clear();

        //Input the from date
        driver.findElement(By.cssSelector("#applyleave_txtFromDate")).sendKeys("2019-10-10");

        //Clear the default text in the "To Date" field
        driver.findElement(By.cssSelector("#applyleave_txtToDate")).clear();

        //Input the to date
        driver.findElement(By.cssSelector("#applyleave_txtToDate")).sendKeys("2019-10-20");

        //Add a comment
        driver.findElement(By.cssSelector("#applyleave_txtComment")).sendKeys("Attending a function.");

        //Click on the apply button
        driver.findElement(By.cssSelector("#applyBtn")).click();
    }

    @AfterTest()
    public void afterTest(){
        driver.close();
    }
}