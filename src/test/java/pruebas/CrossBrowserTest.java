package pruebas;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTest {

	WebDriver driver;

	String url = "https://automationexercise.com/products";

	@BeforeMethod
	@Parameters("navegador")
	public void setUP(String navegador) {

		if (navegador.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new FirefoxDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@Test
	public void BuscarTexto() {
		WebElement txtBusqueda = driver.findElement(By.id("search_product"));
		txtBusqueda.sendKeys("dress");
		txtBusqueda.sendKeys(Keys.ENTER);
	}

	@AfterMethod
	public void CerrarNagvegador() {
		driver.quit();
	}
}