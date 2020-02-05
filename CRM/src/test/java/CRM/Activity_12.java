package CRM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity_12 {
    private WebDriver driver;
    
    @BeforeClass
    public void beforeTest(){
        driver = new FirefoxDriver();
        
        //Navigate to login page and login
        driver.get("https://alchemy.hguy.co/crm/");
        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("bigbutton")).click();
        
    }

    @Test
    public  void test() {
        WebDriverWait wait;
        Actions action;
        wait = new WebDriverWait(driver, 10);
        action = new Actions(driver);

        
        // Navigate to meetings
        WebElement activities = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"grouptab_3\"]")));
        action.moveToElement(activities).moveToElement(driver.findElement(By.xpath("//*[@id=\"moduleTab_9_Meetings\"]"))).click().build().perform();
        WebElement scheduleMeeting = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='actionMenuSidebar']/ul/li[1]")));
        scheduleMeeting.click();

        // Create new meeting
        WebElement meetingName = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
        meetingName.sendKeys("Test");
        driver.findElement(By.id("date_start_date")).sendKeys("01/28/2019");

        // Search using first name and add member
        WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_first_name")));
        firstName.sendKeys("chris");
        driver.findElement(By.id("invitees_search")).click();
        WebElement addMember = wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_add_1")));
        addMember.click();
        driver.findElement(By.id("search_first_name")).clear();

        // Search using last name and add member
        driver.findElement(By.id("search_last_name")).sendKeys("smith");
        driver.findElement(By.id("invitees_search")).click();
        addMember = wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_add_1")));
        addMember.click();
        driver.findElement(By.id("search_last_name")).clear();

        // Search using email id and add member
        driver.findElement(By.id("search_email")).sendKeys("will@example.com");
        driver.findElement(By.id("invitees_search")).click();
        addMember = wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_add_1")));
        addMember.click();
        driver.findElement(By.id("search_email")).clear();

        // Save meeting
        driver.findElement(By.id("SAVE_HEADER")).click();

        //Open Users tabs
        driver.findElement(By.xpath("//*[@id='whole_subpanel_users']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='list_subpanel_users']/table/tbody/tr[2]/td[3]/a")));
        String user1 = driver.findElement(By.xpath("//*[@id=\"list_subpanel_users\"]/table/tbody/tr[2]/td[3]/a")).getText();
        Assert.assertEquals(user1,"Chris Olliver");
        String user2 = driver.findElement(By.xpath("//*[@id=\"list_subpanel_users\"]/table/tbody/tr[3]/td[3]/a")).getText();
        Assert.assertEquals(user2,"Sarah Smith");
        String user3 = driver.findElement(By.xpath("//*[@id=\"list_subpanel_users\"]/table/tbody/tr[4]/td[3]/a")).getText();
        Assert.assertEquals(user3,"Will Westin");
    }

    @AfterClass
    public void afterTest(){
        driver.close();
    }
}