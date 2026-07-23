
package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import paginas.PaginaLogin;

public class PracticaM4 {

	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	PaginaLogin login;

	@BeforeMethod
	public void setUP() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-blink-features=AutomationControlled");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(url);
	}

	@Test
	public void loginUser(){
		login = new PaginaLogin(driver);
		login.ingresarDatos("standard_user", "secret_sauce");
		Assert.assertEquals(login.getTitleForm(), "Products");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}