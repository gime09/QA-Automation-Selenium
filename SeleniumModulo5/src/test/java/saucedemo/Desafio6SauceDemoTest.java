package saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils; // DESAFÍO 6
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // DESAFÍO 6
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DatosExcel;

import java.io.File; // DESAFÍO 6
import java.io.IOException; // DESAFÍO 6
import java.time.Duration;

/**
 * Desafío Módulo 6: Sauce Demo
 * Basado en SauceDemoTest, incorpora ejecución headless e incógnito,
 * parametrizadas desde testng.xml, con captura de evidencias.
 */
public class Desafio6SauceDemoTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String RUTA_EXCEL = "src/test/resources/datos_sauce.xlsx";
    private static final String HOJA_EXCEL = "Sauce";
    private static final String URL = "https://www.saucedemo.com";

    @DataProvider(name = "datosDesdeExcel")
    public Object[][] proveerDatos() {
        return DatosExcel.leerExcel(RUTA_EXCEL, HOJA_EXCEL);
    }

    // ─── Setup y Teardown ─────────────────────────────────────────────────────
    @BeforeMethod
    @Parameters({"headless", "incognito"}) // DESAFÍO 6
    public void setUp(@Optional("false") String headless,
                      @Optional("false") String incognito) {

        WebDriverManager.chromedriver().setup();

        // DESAFÍO 6: ChromeOptions parametrizado
        ChromeOptions options = new ChromeOptions();

        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        if (Boolean.parseBoolean(incognito)) {
            options.addArguments("--incognito");
        }

        driver = new ChromeDriver(options);
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

        // DESAFÍO 6: evidencia de la configuración del navegador (headless/incógnito)
        driver.get("chrome://version/");
        tomarEvidencia("config_navegador_" + username);

        driver.get(URL);
        login(username, password);
        tomarEvidencia("post_login_" + username); // DESAFÍO 6

        agregarProductoAlCarrito();
        irAlCarrito();
        iniciarCheckout();

        boolean pudoAvanzar = completarInformacionPersonal(firstname, lastname, zipcode);

        if (pudoAvanzar) {
            verificarResumenOrden();
            finalizarOrden();
            tomarEvidencia("orden_finalizada_" + username); // DESAFÍO 6
            System.out.println("✓ PreOrden completada exitosamente para: " + firstname + " " + lastname);
        } else {
            tomarEvidencia("bug_detectado_" + username); // DESAFÍO 6
            System.out.println("⚠ Flujo interrumpido: Se detectó un bug en la página al usar el usuario '" + username + "'.");
        }
    }

    // ─── Métodos auxiliares (sin cambios respecto al original) ────────────────

    private void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "El login falló para el usuario: " + username);
    }

    private void agregarProductoAlCarrito() {
        WebElement btnAgregar = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".btn_inventory"))
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
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        WebElement btnCheckout = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("checkout"))
        );

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

        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        WebElement btnContinue = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        btnContinue.click();

        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("checkout-step-two"),
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
            ));
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnFinish);

        wait.until(ExpectedConditions.urlContains("checkout-complete"));
        WebElement confirmacion = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))
        );
        Assert.assertEquals(confirmacion.getText(), "Thank you for your order!",
                "El mensaje de confirmación no es el esperado");
    }

    // DESAFÍO 6: método de evidencia
    private void tomarEvidencia(String nombreArchivo) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destino = new File("evidencias/" + nombreArchivo + "_"
                    + System.currentTimeMillis() + ".png");
            destino.getParentFile().mkdirs();
            FileUtils.copyFile(screenshot, destino);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
