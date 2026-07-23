package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class PaginaLogin {

	WebDriver driver;

	// Locators (Sauce Demo)
	By usernameField = By.id("user-name");
	By passwordField = By.id("password");
	By loginButton = By.id("login-button");
	By titleForm = By.className("title");

	// Constructor
	public PaginaLogin(WebDriver driver) {
		this.driver = driver;
	}

	// Método para Ingresar Datos
	public void ingresarDatos(String username, String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));

		driver.findElement(usernameField).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
	}

	// Método para Obtener Título
	public String getTitleForm() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(titleForm));
		return driver.findElement(titleForm).getText();
	}

	public void pagina(String standardUser, String secretSauce) {
	}
}