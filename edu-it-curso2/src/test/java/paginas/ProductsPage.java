
package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductsPage {
    WebDriver driver;

    By addToCartButtons = By.cssSelector("button.btn_inventory");
    By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void agregarPrimerProducto() {
        List<WebElement> botones = driver.findElements(addToCartButtons);
        botones.get(0).click();
    }

    public void irAlCarrito() {
        driver.findElement(cartIcon).click();
    }
}