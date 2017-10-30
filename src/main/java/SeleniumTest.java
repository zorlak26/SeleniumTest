import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {
    private String url;
    protected WebDriver driver;

    public SeleniumTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Carlos\\Desktop\\Selenium\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    public SeleniumTest(String url) {
        this.url = url;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Carlos\\Desktop\\Selenium\\chromedriver.exe");
        this.driver = new ChromeDriver();

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void abrirBrowser(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(this.url);
        driver.manage().window().maximize();
    }
    public void cerrarBrowser(){ driver.quit();}

    public static void main(String[] args) {
        // Prueba Servel
        SeleniumTest servelTest = new SeleniumTest("https://consulta.servel.cl/");
        try {
            servelTest.abrirBrowser();
            servelTest.driver.findElement(By.id("RunIn")).sendKeys("15282202-2");
            //Thread.sleep(5000);
            //servelTest.driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[5]")).click();
            //submit()
            servelTest.driver.findElement(By.id("btnConsulta")).click();
            Thread.sleep(1000);
            String outText = servelTest.driver.findElement(By.id("RunNotValid")).getText();
            if (outText.equals("Debe contestar la validación de Captcha")){
                System.out.println("PRUEBA SERVEL OK");
            }
            else {
                throw new NoSuchElementException("Texto no coincide");
            }
        }
        catch (InterruptedException | NoSuchElementException e){
            System.out.println("PRUEBA SERVEL FALLIDA");
            e.printStackTrace();
        }
        finally {
            servelTest.cerrarBrowser();
        }
        // Prueba Correos
        SeleniumTest correosTest = new SeleniumTest("http://www.correos.cl/");
        try {
            correosTest.abrirBrowser();
            correosTest.driver.findElement(By.xpath("//*[@id=\"codigo_postal\"]/input[1]")).sendKeys("Pedro Marín");
            correosTest.driver.findElement(By.xpath("//*[@id=\"codigo_postal\"]/input[2]")).sendKeys("2614");
            correosTest.driver.findElement(By.xpath("//*[@id=\"codigo_postal\"]/input[3]")).sendKeys("Ñuñoa");
            correosTest.driver.findElement(By.id("btnCodigoPostal")).click();
            Thread.sleep(5000);
            if(correosTest.driver.getPageSource().contains("7770204")){
                System.out.println("PRUEBA CORREOS OK");
            }
            else {
                throw new NoSuchElementException("Texto no encontrado");
            }
        }
        catch (InterruptedException | NoSuchElementException e){
            System.out.println("PRUEBA CORREOS FALLIDA");
            e.printStackTrace();
        }
        finally {
            correosTest.cerrarBrowser();
        }
        // Prueba Falabella
        SeleniumTest falabellaTest = new SeleniumTest("http://www.falabella.com/falabella-cl/");
        try {
            falabellaTest.abrirBrowser();
            WebDriverWait wait = new WebDriverWait(falabellaTest.driver, 1);
            //llena
            falabellaTest.driver.findElement(By.xpath("//*[@id=\"searchQuestion\"]")).sendKeys("5607792");
            //click en el buscar
            WebElement btnBuscar = falabellaTest.driver.findElement(By.xpath("//*[@id=\"searchForm\"]/a/i"));
            wait.until(ExpectedConditions.elementToBeClickable(btnBuscar));
            btnBuscar.click();
            //agregar Bolsa
            Thread.sleep(2000);
            WebElement btnAgregar = falabellaTest.driver.findElement(By.xpath("//*[@id=\"fbra_browseMainProduct\"]/div/div/div[2]/div/div[6]/div/div[2]/button"));
            wait.until(ExpectedConditions.elementToBeClickable(btnAgregar));
            btnAgregar.click();
            Thread.sleep(2000);
            WebElement btnVerCarro = falabellaTest.driver.findElement(By.className("fb-btn fb-btn-primary fb-added-to-basket__cta fb-added-to-basket__cta--basket"));
            wait.until(ExpectedConditions.elementToBeClickable(btnVerCarro));
        }
        catch (InterruptedException | NoSuchElementException e){
            System.out.println("PRUEBA FALABELLA FALLIDA");
            e.printStackTrace();
        }
        finally {
            falabellaTest.cerrarBrowser();
        }
        // Prueba Lider
        SeleniumTest liderTest = new SeleniumTest("https://www.lider.cl");
        try {
            liderTest.abrirBrowser();
            WebElement search;
            WebElement button;
            search = liderTest.driver.findElement(By.id("searchtextinput"));
            search.sendKeys("UN40J5200");
            search.submit();
            Thread.sleep(5000);
            button = liderTest.driver.findElement(By.id("add-button"));
            button.click();
            Thread.sleep(2000);
            search = liderTest.driver.findElement(By.id("searchtextinput"));
            search.sendKeys("WA12J5712LW");
            search.submit();
            Thread.sleep(5000);
            button = liderTest.driver.findElement(By.id("add-button"));
            button.click();
            Thread.sleep(2000);
            button = liderTest.driver.findElement(By.id("buy-button"));
            button.click();
            Thread.sleep(5000);
            List<WebElement> listado = liderTest.driver.findElements(By.className("product_display_name"));
            List<String> outText = new ArrayList<>();
            for (WebElement elemento : listado){
                outText.add(elemento.getText());
                System.out.println(elemento.getText());
            }
            if(outText.contains("LED 40 Full HD Smart TV / UN40J5200") && outText.contains("Lavadora Superior 12 Kg. / WA12J5712LW/ZS")){
                System.out.println("PRUEBA LIDER OK");
            }
            else {
                throw new NoSuchElementException("Texto no encontrado");
            }
        }
        catch (InterruptedException | NoSuchElementException e){
            System.out.println("PRUEBA LIDER FALLIDA");
            e.printStackTrace();
        }
        finally {
            liderTest.cerrarBrowser();
        }
    }
}
