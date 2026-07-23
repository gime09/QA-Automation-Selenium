package pruebas;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Etapa61Test {

    WebDriver driver;
    String urlTest = "https://www.wikipedia.org";

    @BeforeSuite
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(urlTest);
    }

    @Test
    public void buscarPalabra() {
        String palabra = "Selenium";
        driver.findElement(By.id("searchInput")).sendKeys(palabra, Keys.ENTER);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}