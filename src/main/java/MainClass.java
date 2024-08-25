import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MainClass {

    public static <WebElements> void main(String[] args) {

        //System.setProperty("webdriver.xxx.driver","xxx");
/*
        ChromeOptions opt = new ChromeOptions();
        opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriver driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form/");

        Random random = new Random();
        WebElement elementFirstName_id = driver.findElement(By.id("firstName"));
        elementFirstName_id.sendKeys("Alexey");
        WebElement elementFirstName_css = driver.findElement(By.cssSelector("div#userName-wrapper>div.col-sm-6 >input#firstName"));
        WebElement elementFirstName_xPath = driver.findElement(By.xpath("//form[@id='userForm']//input[@id='firstName']"));
        //driver.quit();
*/
    }
}
