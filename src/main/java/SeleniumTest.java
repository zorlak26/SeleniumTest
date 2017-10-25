import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;

public class SeleniumTest {
    private WebDriver driver;

    public SeleniumTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Carlos\\Desktop\\Selenium\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    public void abrirBrowser(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("https://www.google.cl");
    }

    public void buscar() {
        driver.findElement(By.id("lst-ib")).sendKeys("hola");
        driver.findElement(By.id("lst-ib")).sendKeys(Keys.RETURN);
        //driver.findElement(By.xpath("//*[@id=\"sbtc\"]/div[2]/div[2]/div[1]/div/ul/li[11]/div/span[1]/span/input"));
    }

    public static void main(String[] args) {
        SeleniumTest test = new SeleniumTest();
        test.abrirBrowser();
        test.buscar();
    }
}
