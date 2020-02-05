package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_3 {
    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser and navigate to http://alchemy.hguy.co/crm
        driver.get("http://alchemy.hguy.co/crm");

        //Maximize the browser
        driver.manage().window().maximize();
    }

    @Test
    public void Test() {
        //Get the copyright text
        String copyright_text = driver.findElement(By.cssSelector("#admin_options")).getText();

        //Print the text
        System.out.println(copyright_text);

    }

    @AfterTest
    public void afterTest() {
        //Close the browser
        driver.close();
    }

}