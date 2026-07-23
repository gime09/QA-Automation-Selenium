package paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PaginaInicio {
	// Elementos
	@FindBy(xpath="//a[contains(text(),'Sign in')]")
	WebElement lnkSignIn;
	
	// Constructor
	public PaginaInicio(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this); // Inicializar los elementos de la clase
	}
	
	// Acciones
	public void hacerClicEnSignIn() {
		lnkSignIn.click();
	}
}
