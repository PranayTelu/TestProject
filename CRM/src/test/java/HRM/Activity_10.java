package HRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_10 {
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
        //Click on the "Recruitment" menu item
        driver.findElement(By.cssSelector("#menu_recruitment_viewRecruitmentModule > b")).click();

        //Click on the "Vacancies" menu item
        driver.findElement(By.cssSelector("#menu_recruitment_viewJobVacancy")).click();

        //Click on the "Add" button
        driver.findElement(By.cssSelector("#btnAdd")).click();

        //Get the "Job Title" select element
        Select job_title = new Select(driver.findElement(By.id("addJobVacancy_jobTitle")));

        //Set the "Job Title" to "DevOps Engineer"
        job_title.selectByVisibleText("DevOps Engineer");

        //Set the "Vacancy Name"
        driver.findElement(By.id("addJobVacancy_name")).sendKeys("DevOps Engineer Wanted");

        //Set the Hiring Manager to "Admin"
        driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("Admin Admin");

        //Set the number of positions to 2
        driver.findElement(By.id("addJobVacancy_noOfPositions")).sendKeys("2");

        //Click on "Save"
        driver.findElement(By.id("btnSave")).click();
    }

    @AfterTest()
    public void afterTest(){
        driver.close();
    }
}