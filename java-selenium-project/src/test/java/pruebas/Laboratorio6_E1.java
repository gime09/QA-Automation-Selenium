package pruebas;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;

public class Laboratorio6_E1 {
	WebDriver driver;
	String urlTest = "http://www.automationpractice.pl/index.php";
	
	@BeforeSuite
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(urlTest);
		driver.manage().window().maximize();
	}
	
	@Test
	public void buscarPalabra() {
		// Encontrar el buscador
		WebElement txtBuscador = driver.findElement(By.id("search_query_top"));
		
		// Escribir la palabra a buscar
		txtBuscador.sendKeys("dress");
		
		// Simular presionar la tecla ENTER
		txtBuscador.sendKeys(Keys.ENTER);
	}
	
	@AfterSuite
	public void cerrar() {
		if (driver != null) {
			driver.close();
		}
	}
}
