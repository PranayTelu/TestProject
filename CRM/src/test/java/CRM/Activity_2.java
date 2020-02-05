package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_2 {
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
    public void test() {
        //Get the header image element
        WebElement header_image = driver.findElement(By.cssSelector(".companylogo > img:nth-child(1)"));

        //Print the url of the header image
        System.out.println(header_image.getAttribute("src"));

    }

    @AfterTest
    public void afterTest() {
        //Close the browser
        driver.close();
    }

}