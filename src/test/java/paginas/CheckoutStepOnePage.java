
package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage {
    WebDriver driver;

    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By zipField = By.id("postal-code");
    By continueButton = By.id("continue");

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
    }

    public void completarDatos(String nombre, String apellido, String cp) {
        driver.findElement(firstNameField).sendKeys(nombre);
        driver.findElement(lastNameField).sendKeys(apellido);
        driver.findElement(zipField).sendKeys(cp);
        driver.findElement(continueButton).click();
    }
}