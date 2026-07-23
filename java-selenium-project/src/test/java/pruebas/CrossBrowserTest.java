package pruebas;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTest {
	WebDriver driver;
	String chromeDriverPath = "..\\EducacionIt\\Drivers\\chromedriver.exe";
	String firefoxDriverPath = "..\\EducacionIt\\Drivers\\geckodriver.exe";
	String urlTest ="http://automationpractice.com/index.php";
	
	@BeforeTest
	@Parameters("navegador")
	public void setUp(String navegador) {
		if (navegador.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		} else if (navegador.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			driver = new FirefoxDriver();
		}
		
		driver.get(urlTest);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void buscarPalabra() throws IOException {
		// Acciones del caso de prueba Buscar Palabra
		WebElement txtBuscador = driver.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress");

		txtBuscador.sendKeys(Keys.ENTER);
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
