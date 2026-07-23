package automationpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DatosExcel;

import java.time.Duration;

/**
 * Etapa 5.1 — Login con @DataProvider leyendo Excel
 * Sitio: automationpractice.pl
 */
public class Etapa51Test {

    private WebDriver driver;
    private WebDriverWait wait;

    // ─── Constantes ───────────────────────────────────────────────────────────
    private static final String RUTA_EXCEL = "src/test/resources/datos_login.xlsx";
    private static final String HOJA_EXCEL = "Login";
    private static final String URL = "https://automationpractice.pl/index.php?controller=authentication";

    // ─── @DataProvider — lee el Excel ─────────────────────────────────────────
    @DataProvider(name = "datosLogin")
    public Object[][] proveerDatos() {
        return DatosExcel.leerExcel(RUTA_EXCEL, HOJA_EXCEL);
    }

    // ─── Setup ────────────────────────────────────────────────────────────────
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
    }

    // ─── Test ─────────────────────────────────────────────────────────────────
    @Test(dataProvider = "datosLogin")
    public void inicioSesionInvalido(String email, String password) {

        System.out.println("=== Probando con: " + email + " ===");

        // Ingresar email
        WebElement campoEmail = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email"))
        );
        campoEmail.clear();
        campoEmail.sendKeys(email);

        // Ingresar password
        WebElement campoPassword = driver.findElement(By.id("passwd"));
        campoPassword.clear();
        campoPassword.sendKeys(password);

        // Click en Sign In
        driver.findElement(By.id("SubmitLogin")).click();

        // Verificar que seguimos en la página de autenticación (login inválido)
        WebElement tituloFormulario = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.page-heading"))
        );

        String textoTitulo = tituloFormulario.getText();
        System.out.println("Título del formulario: " + textoTitulo);

        Assert.assertEquals(textoTitulo, "AUTHENTICATION",
                "Se esperaba permanecer en la página de AUTHENTICATION");

        System.out.println("✓ Login inválido verificado correctamente para: " + email);
    }

    // ─── Teardown ─────────────────────────────────────────────────────────────
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}