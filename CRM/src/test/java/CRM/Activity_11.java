package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity_11 {

    public static void main(String[] args) {

        WebDriver driver=new FirefoxDriver();

        // Path of the file to upload
        String workingDir = System.getProperty("user.dir");
        String filepath = workingDir + "/leads.csv";

        // Navigate to login page
        driver.get("https://alchemy.hguy.co/crm/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        Actions action = new Actions(driver);

        // Login to CRM
        WebElement username = driver.findElement(By.id("user_name"));
        System.out.println("found user name");
        WebElement password = driver.findElement(By.id("username_password"));
        System.out.println("found password");

        username.sendKeys("admin");
        System.out.println("entered username");

        password.sendKeys("pa$$w0rd");
        System.out.println("entered password");

        driver.findElement(By.id("bigbutton")).click();

        // Navigate to Leads page
        WebElement leads = driver.findElement(By.id("grouptab_0"));
        action.moveToElement(leads).moveToElement(driver.findElement(By.xpath("//*[@id=\"moduleTab_9_Leads\"]"))).click().build().perform();
        System.out.println("Clicked on leads");
        WebElement importLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='actionMenuSidebar']/ul/li[4]")));
        importLeads.click();

        //Upload file
        WebElement upload = driver.findElement(By.id("userfile"));
        upload.sendKeys(filepath);

        driver.findElement(By.id("gonext")).click();
        driver.findElement(By.id("gonext")).click();
        driver.findElement(By.id("gonext")).click();
        driver.findElement(By.id("importnow")).click();

        driver.close();
    }
}