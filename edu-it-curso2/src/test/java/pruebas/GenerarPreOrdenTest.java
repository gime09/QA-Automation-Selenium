package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import paginas.*;

public class GenerarPreOrdenTest {

    WebDriver driver;
    String url = "https://www.saucedemo.com/";

    @BeforeMethod
    @Parameters("navegador")
    public void setUp(String navegador) {
        if (navegador.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void generarPreOrden() {
        PaginaLogin login = new PaginaLogin(driver);
        login.pagina("standard_user", "secret_sauce");

        ProductsPage productos = new ProductsPage(driver);
        productos.agregarPrimerProducto();
        productos.irAlCarrito();

        CartPage carrito = new CartPage(driver);
        carrito.irACheckout();

        CheckoutStepOnePage paso1 = new CheckoutStepOnePage(driver);
        paso1.completarDatos("Juan", "Perez", "5500");

        CheckoutStepTwoPage paso2 = new CheckoutStepTwoPage(driver);
        paso2.finalizarCompra();

        CheckoutCompletePage confirmacion = new CheckoutCompletePage(driver);
        Assert.assertEquals(confirmacion.obtenerMensaje(), "Thank you for your order!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}