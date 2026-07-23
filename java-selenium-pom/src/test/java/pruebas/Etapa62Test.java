package pruebas;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Etapa62Test {

    WebDriver driver;
    String urlTest = "https://www.wikipedia.org";

    @BeforeSuite
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.get(urlTest);
    }

    @Test
    public void buscarPalabraHeadless() throws IOException {
        String palabra = "Selenium";
        driver.findElement(By.id("searchInput")).sendKeys(palabra, Keys.ENTER);

        File captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(captura, new File("capturas/busqueda_headless.png"));
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}