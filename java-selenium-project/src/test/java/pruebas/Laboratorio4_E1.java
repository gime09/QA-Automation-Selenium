package pruebas;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import paginas.PaginaInicio;
import paginas.PaginaLogin;

public class Laboratorio4_E1 {
	WebDriver driver;
	String driverPath = "..\\EducacionIt\\Drivers\\chromedriver.exe";
	String urlTest ="http://automationpractice.com/index.php";
	
	PaginaInicio inicio;
	PaginaLogin login;

	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(urlTest);
	}

	@BeforeMethod
	public void irASignIn() {
		inicio = new PaginaInicio(driver);
		inicio.hacerClicEnSignIn();
	}
	
	@Test
	public void irRegistroLogin() {
		login = new PaginaLogin(driver);
		login.enterEmail("correo@gmail.com");
	
		Assert.assertEquals(login.getTextTitleForm(), "AUTHENTICATION");
	}
	
	@Test
	public void crearCuentaInvalida() throws IOException {
		login = new PaginaLogin(driver);
		login.enterEmail("correo@gmail.com");
		
		String texto = login.getTextTitleForm();
		Assert.assertEquals(texto, "AUTHENTICATION");
		
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\EducacionIt\\Evidencias\\pantalla_crearCuentaInvalida.png"));
	}
	
	@AfterMethod
	public void goInicio() {
		login.gotoInicio();
	}
	
	@AfterSuite
	public void cerrar() {
		driver.close();
	}
}
