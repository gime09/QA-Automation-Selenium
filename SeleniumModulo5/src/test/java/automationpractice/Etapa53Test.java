package automationpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

/**
 * Etapa 5.3 — Manejo de alertas
 * Sitio: https://demoqa.com/alerts
 */
public class Etapa53Test {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL = "https://demoqa.com/alerts";
    private static final String TEXTO_ALERTA = "Hola desde Selenium";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
    }

    @Test
    public void interactuarConAlerta() {
        // La página tiene 4 botones "Click me"
        // Necesitamos el CUARTO (índice 3)
        List<WebElement> botones = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("button[id$='Button']")
                )
        );

        System.out.println("Cantidad de botones encontrados: " + botones.size());

        // Click en el cuarto botón (índice 3) — es el prompt alert
        botones.get(3).click();

        // Esperar que aparezca la alerta
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alerta = driver.switchTo().alert();

        System.out.println("Texto de la alerta: " + alerta.getText());

        // Escribir texto en el campo de la alerta (prompt)
        alerta.sendKeys(TEXTO_ALERTA);

        // Click en OK
        alerta.accept();

        // Verificar el resultado que muestra la página tras aceptar
        WebElement resultado = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("promptResult"))
        );

        System.out.println("Resultado en página: " + resultado.getText());
        System.out.println("✓ Alerta manejada correctamente");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}