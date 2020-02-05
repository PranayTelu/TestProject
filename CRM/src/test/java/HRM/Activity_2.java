package HRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_2 {
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
        //Get the header image
        WebElement header = driver.findElement(By.cssSelector("#divLogo>img"));

        //Get the url of the header image
        String url = header.getAttribute("src");

        //Print the URL to the console
        System.out.println(url);
    }

    @AfterTest()
    public void afterTest(){
        driver.close();
    }

}
