package automationpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;

/**
 * Etapa 5.2 — Carga de archivo en formulario
 * Sitio: http://demo.guru99.com/test/upload/
 */
public class Etapa52Test {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL = "http://demo.guru99.com/test/upload/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
    }


    @Test
    public void subirArchivo() {
        // Obtener ruta absoluta del archivo a subir
        File archivo = new File("src/test/resources/foto_empleado.jpg");
        String rutaAbsoluta = archivo.getAbsolutePath();

        System.out.println("Subiendo archivo: " + rutaAbsoluta);

        // Localizar el input de tipo file y enviarle la ruta del archivo
        WebElement inputArchivo = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("uploadfile_0"))
        );
        inputArchivo.sendKeys(rutaAbsoluta);

        // Aceptar términos y condiciones
        WebElement checkboxTerminos = driver.findElement(By.id("terms"));
        if (!checkboxTerminos.isSelected()) {
            checkboxTerminos.click();
        }

        // --- SOLUCIÓN APLICADA AQUÍ ---
        // 1. Usamos el ID exacto del botón de Guru99 (submitbutton)
        // 2. Obligamos a Selenium a esperar hasta que el botón sea "clickeable"
        WebElement btnSubmit = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("submitbutton"))
        );
        btnSubmit.click();

        // Verificar que el upload fue exitoso
        WebElement mensaje = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        // A veces en Guru99 el mensaje de éxito se encuentra en este selector
                        By.cssSelector("h3#res")
                )
        );

        System.out.println("Resultado: " + mensaje.getText());
        Assert.assertTrue(
                mensaje.getText().contains("1 file(s) have been successfully uploaded") || mensaje.getText().contains("has been successfully uploaded"),
                "El archivo no se subió correctamente"
        );

        System.out.println("✓ Archivo subido exitosamente");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}