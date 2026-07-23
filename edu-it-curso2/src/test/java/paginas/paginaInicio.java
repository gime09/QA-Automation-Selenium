package paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class paginaInicio {
    WebDriver driver;

    // EL ARMA SECRETA: XPath buscando por el título exacto del botón
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    WebElement lnkSignIn;

    // Constructor con 15 segundos de escudo
    public paginaInicio(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void clicEnSignIn() {
        lnkSignIn.click();
    }
}