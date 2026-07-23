package pruebas;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Laboratorio6_E2 {
	WebDriver driver;
	String urlTest = "http://www.automationpractice.pl/index.php";
	
	@BeforeSuite
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		// Es buena práctica agregar el tamaño de ventana en headless
		options.addArguments("--window-size=1920,1080");
		
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(urlTest);
	}
	
	@Test
	public void buscarPalabra() throws IOException {
		// Encontrar el buscador
		WebElement txtBuscador = driver.findElement(By.id("search_query_top"));
		
		// Escribir la palabra a buscar
		txtBuscador.sendKeys("dress");
		
		// Simular presionar la tecla ENTER
		txtBuscador.sendKeys(Keys.ENTER);
		
		// Esperar unos segundos para asegurar que cargó el resultado de la búsqueda
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Capturar pantalla
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\EducacionIt\\Evidencias\\pantalla_Laboratorio6_E2.png"));
	}
	
	@AfterSuite
	public void cerrar() {
		if (driver != null) {
			driver.close();
		}
	}
}
