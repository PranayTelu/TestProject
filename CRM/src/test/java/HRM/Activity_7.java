package HRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_7 {
    private WebDriver driver;
        private WebDriverWait wait;

    @BeforeTest()
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);

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
        //Wait until the Menu is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menu_pim_viewMyDetails")));

        //Find the "My Info" menu item and click on it
        driver.findElement(By.cssSelector("#menu_pim_viewMyDetails")).click();

        //Wait for the sidebar to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sidenav")));

        //Find the "Qualifications" menu item in the sidebar and click it
        driver.findElement(By.cssSelector("#sidenav > li:nth-child(9) > a:nth-child(1)")).click();

        //Click the "Add" button in the "Work Experience" section
        driver.findElement(By.cssSelector("#addWorkExperience")).click();

        //Fill in the Company name
        driver.findElement(By.cssSelector("#experience_employer")).sendKeys("Best Company PVT. LTD.");

        //Fill in the Job Title
        driver.findElement(By.cssSelector("#experience_jobtitle")).sendKeys("Automation Tester");

        //Clear the default text in the "From" section
        driver.findElement(By.cssSelector("#experience_from_date")).clear();

        //Fill in the "From" section
        driver.findElement(By.cssSelector("#experience_from_date")).sendKeys("2019-05-14");

        //Clear the default text in the "To" section
        driver.findElement(By.cssSelector("#experience_to_date")).clear();

        //Fill in the "To" section
        driver.findElement(By.cssSelector("#experience_to_date")).sendKeys("2020-11-21");

        //Fill in the "Comment" section
        driver.findElement(By.cssSelector("#experience_comments")).sendKeys("Worked closely with Selenium and Java to write and maintain many test suites.");

        //Click the save button
        driver.findElement(By.cssSelector("#btnWorkExpSave")).click();

    }

    @AfterTest()
    public void afterTest(){
        driver.close();
    }
}
