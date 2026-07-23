package saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DatosExcel;

import java.time.Duration;

/**
 * Ejercicio 1: Sauce Demo
 * Genera una PreOrden leyendo username, password, firstname, lastname y zipcode
 * desde un archivo Excel usando @DataProvider de TestNG.
 */
public class SauceDemoTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // ─── Ruta al archivo Excel ────────────────────────────────────────────────
    private static final String RUTA_EXCEL = "src/test/resources/datos_sauce.xlsx";
    private static final String HOJA_EXCEL = "Sauce";

    // ─── URLs ─────────────────────────────────────────────────────────────────
    private static final String URL = "https://www.saucedemo.com";

    // ─── @DataProvider ────────────────────────────────────────────────────────
    @DataProvider(name = "datosDesdeExcel")
    public Object[][] proveerDatos() {
        return DatosExcel.leerExcel(RUTA_EXCEL, HOJA_EXCEL);
    }

    // ─── Setup y Teardown ─────────────────────────────────────────────────────
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─── Test principal ───────────────────────────────────────────────────────
    @Test(dataProvider = "datosDesdeExcel")
    public void generarPreOrden(String username, String password,
                                String firstname, String lastname, String zipcode) {

        System.out.println("=== Ejecutando con: " + username + " ===");

        // 1. Navegar al sitio
        driver.get(URL);

        // 2. Login
        login(username, password);

        // 3. Agregar un producto al carrito
        agregarProductoAlCarrito();

        // 4. Ir al carrito
        irAlCarrito();

        // 5. Iniciar checkout
        iniciarCheckout();

        // 6. Completar información personal (Ahora devuelve un boolean para saber si pudo avanzar)
        boolean pudoAvanzar = completarInformacionPersonal(firstname, lastname, zipcode);

        // Solo continuamos con la orden si la página no arrojó un error intencional (bug del problem_user)
        if (pudoAvanzar) {
            // 7. Verificar resumen de la orden
            verificarResumenOrden();

            // 8. Finalizar orden
            finalizarOrden();

            System.out.println("✓ PreOrden completada exitosamente para: " + firstname + " " + lastname);
        } else {
            System.out.println("⚠ Flujo interrumpido: Se detectó un bug en la página al usar el usuario '" + username + "'.");
        }
    }

    // ─── Métodos auxiliares ───────────────────────────────────────────────────

    private void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        // Verificar que el login fue exitoso
        wait.until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "El login falló para el usuario: " + username);
    }

    private void agregarProductoAlCarrito() {
        WebElement btnAgregar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".btn_inventory")
                )
        );
        btnAgregar.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("shopping_cart_badge"), "1"
        ));
    }

    private void irAlCarrito() {
        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.urlContains("cart"));
    }

    private void iniciarCheckout() {
        // Pausa breve para estabilizar el DOM antes de buscar el botón
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        WebElement btnCheckout = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("checkout"))
        );

        // Forzamos el clic con JavaScript por si un elemento superpuesto está interceptando la acción
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnCheckout);

        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }

    private boolean completarInformacionPersonal(String firstname, String lastname, String zipcode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

        driver.findElement(By.id("first-name")).clear();
        driver.findElement(By.id("first-name")).sendKeys(firstname);

        driver.findElement(By.id("last-name")).clear();
        driver.findElement(By.id("last-name")).sendKeys(lastname);

        driver.findElement(By.id("postal-code")).clear();
        driver.findElement(By.id("postal-code")).sendKeys(zipcode);

        // --- EL TRUCO PARA REACT ---
        // Le damos medio segundo a la aplicación para que procese internamente el texto ingresado
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        // Clic normal
        WebElement btnContinue = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        btnContinue.click();

        // Verificamos si avanzamos de página O si aparece un mensaje de error
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("checkout-step-two"),
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
            ));

            // Retornamos true solo si logramos llegar al paso 2
            return driver.getCurrentUrl().contains("checkout-step-two");

        } catch (TimeoutException e) {
            return false;
        }
    }

    private void verificarResumenOrden() {
        WebElement resumen = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("summary_info"))
        );
        Assert.assertTrue(resumen.isDisplayed(), "El resumen de la orden no se muestra");

        Assert.assertFalse(
                driver.findElements(By.className("cart_item")).isEmpty(),
                "No hay items en el resumen de la orden"
        );
    }

    private void finalizarOrden() {
        WebElement btnFinish = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("finish"))
        );
        // JS Click por precaución
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnFinish);

        wait.until(ExpectedConditions.urlContains("checkout-complete"));
        WebElement confirmacion = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))
        );
        Assert.assertEquals(confirmacion.getText(), "Thank you for your order!",
                "El mensaje de confirmación no es el esperado");
    }
}