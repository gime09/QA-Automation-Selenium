
package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    WebDriver driver;

    By mensajeConfirmacion = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public String obtenerMensaje() {
        return driver.findElement(mensajeConfirmacion).getText();
    }
}