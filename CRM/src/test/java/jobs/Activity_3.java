package jobs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Activity_3 {
    private WebDriver driver;

    @BeforeTest()
    public void beforeTest(){
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser and navigate to http://alchemy.hguy.co/jobs
        driver.get("http://alchemy.hguy.co/jobs");

        //Maximize the browser
        driver.manage().window().maximize();
    }

    @Test()
    public void Test(){
        //Get the url of the header image on the page
        WebElement header_image = driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/header/div/img"));

        //Print the url of the header image to the console
        System.out.println(header_image.getAttribute("src"));
    }

    @AfterTest
    public void afterTest(){
        //Close the browser
        driver.close();
    }

}
