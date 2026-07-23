package Edit.EducacionIt;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Laboratorio3_Ejercicio1 {
	WebDriver driver;
	String url = "http://automationpractice.com/index.php?controller=authentication";
	String driverPath = "..\\EducacionIt\\Drivers\\chromedriver.exe";
	
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	}
	
	@BeforeTest
	public void irUrl() {
		driver.get(url);
	}
	
	@BeforeClass
	public void maxVentana() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void registroUsuario() {
		// Escribir el correo y hacer clic en el botón Create An Account
		WebElement txtEmail = driver.findElement(By.id("email_create"));
		txtEmail.sendKeys("micorreo" + Math.random() + "@correo.com");
			
		WebElement btnCreate = driver.findElement(By.name("SubmitCreate"));
		btnCreate.click();
				
		// Completar el formulario
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1")));
			
		driver.findElement(By.id("id_gender1")).click();
				
		driver.findElement(By.cssSelector("#customer_firstname")).sendKeys("Rodrigo");
						
		driver.findElement(By.name("passwd")).sendKeys("1q2w3e4r5t");
				
		Select days = new Select(driver.findElement(By.id("days")));
		days.selectByValue("18");
				
		Select months = new Select(driver.findElement(By.id("months")));
		months.selectByIndex(5);
				
		Select years = new Select(driver.findElement(By.xpath("//*[@id='years']")));
		years.selectByValue("1985");
				
		driver.findElement(By.cssSelector("#submitAccount")).click();
		
		Assert.assertEquals(url, driver.getCurrentUrl());
		Assert.assertTrue(url.contentEquals(driver.getCurrentUrl()));
	}
	
	@AfterMethod
	public void capturarPantalla() throws IOException {
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\EducacionIt\\Evidencias\\pantalla.png"));
	}
	
	@AfterClass
	public void finPrueba() {
		System.out.println("Fin de prueba");
	}
	
	@AfterTest
	public void cierreNavegador() {
		driver.close();
	}
	
	@AfterSuite
	public void finSuite () {
		System.out.println("Fin de Suite");
	}
}
