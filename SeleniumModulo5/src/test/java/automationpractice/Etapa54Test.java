package automationpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

/**
 * Etapa 5.4 — Leer celda con valor '8' de una tabla HTML
 * Sitio: http://demo.guru99.com/test/table.html
 */
public class Etapa54Test {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL = "http://demo.guru99.com/test/table.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
    }

    @Test
    public void leerCeldaConValorOcho() {
        // Obtener todas las celdas de la tabla (td y th)
        List<WebElement> celdas = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("table td"))
        );

        System.out.println("Total de celdas encontradas: " + celdas.size());

        String celdaEncontrada = null;

        // Recorrer todas las celdas buscando la que contiene "8"
        for (WebElement celda : celdas) {
            String texto = celda.getText().trim();
            if (texto.equals("8")) {
                celdaEncontrada = texto;
                System.out.println("✓ Celda con valor '8' encontrada: " + texto);
                break;
            }
        }

        // Imprimir resultado
        if (celdaEncontrada != null) {
            System.out.println("Valor de la celda: " + celdaEncontrada);
        } else {
            System.out.println("✗ No se encontró ninguna celda con el valor '8'");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}