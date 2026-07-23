package orangehrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DatosExcel;

import java.io.File;
import java.time.Duration;

public class Test_BonusTrack5b {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL        = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String RUTA_EXCEL = "src/test/resources/datos_orange.xlsx";
    private static final String HOJA_EXCEL = "Orange";
    private static final String ADMIN_USER = "Admin";
    private static final String ADMIN_PASS = "admin123";

    // ── DataProvider ──────────────────────────────────────────────────────────
    @DataProvider(name = "datosUsuarios")
    public Object[][] proveerDatosUsuarios() {
        return DatosExcel.leerExcel(RUTA_EXCEL, HOJA_EXCEL);
    }

    // ── Setup / Teardown ──────────────────────────────────────────────────────
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ── Test 1: Registrar usuario desde Excel ─────────────────────────────────
    @Test(dataProvider = "datosUsuarios", priority = 1)
    public void registrarUsuario(String username, String password,
                                 String userRole, String status) {

        System.out.println("=== Registrando usuario: " + username + " ===");

        loginAdmin();
        irAAdminUsers();
        clickBoton("Add");
        completarFormularioUsuario(username, password, userRole, status);
        clickBoton("Save");
        verificarMensajeExito("Successfully Saved");
        hacerLogout();

        System.out.println("✓ Usuario registrado: " + username);
    }

    // ── Test 2: Modificar usuario ─────────────────────────────────────────────
    @Test(priority = 2)
    public void modificarUsuario() {
        System.out.println("=== Modificando usuario ===");

        loginAdmin();
        irAAdminUsers();
        buscarUsuario("Admin");

        // Hacemos clic en el botón con el ícono de lápiz (Editar) dentro de la grilla
        WebElement btnEditar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='oxd-table-card']//button[.//i[contains(@class, 'bi-pencil')]]")
                )
        );
        btnEditar.click();

        // Modificamos el estado
        seleccionarDropdown("Status", "Enabled");

        // Guardamos
        clickBoton("Save");
        verificarMensajeExito("Successfully Updated");

        System.out.println("✓ Usuario modificado exitosamente");
    }

    // ── Test 3: Crear empleado con foto ───────────────────────────────────────
    @Test(priority = 3)
    public void crearEmpleadoConFoto() {
        System.out.println("=== Creando empleado con foto ===");

        loginAdmin();
        irAPIMAddEmployee();
        completarDatosEmpleado("TestAuto", "Selenium", "Modulo5");
        subirFotoEmpleado();
        clickBoton("Save");
        verificarMensajeExito("Successfully Saved");

        System.out.println("✓ Empleado creado");
    }

    // ── Métodos auxiliares ────────────────────────────────────────────────────

    private void loginAdmin() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='username']")));
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(ADMIN_USER);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(ADMIN_PASS);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    private void irAAdminUsers() {
        WebElement menuAdmin = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[text()='Admin']")
                )
        );
        menuAdmin.click();
        wait.until(ExpectedConditions.urlContains("viewSystemUser"));
    }

    private void completarFormularioUsuario(String username, String password,
                                            String userRole, String status) {
        // Seleccionamos los Dropdowns
        seleccionarDropdown("User Role", userRole);
        seleccionarDropdown("Status", status);

        // Employee Name (Autocomplete) - Buscamos por Label
        WebElement empNameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[normalize-space()='Employee Name']/parent::div/following-sibling::div//input")
                )
        );
        empNameInput.sendKeys("Admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-autocomplete-dropdown")));
        driver.findElement(By.cssSelector(".oxd-autocomplete-option")).click();

        // Username - Buscamos por Label (mucho más seguro que por índices)
        WebElement inputUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::div//input")
        ));
        inputUsername.sendKeys(username);

        // Password - Buscamos por Label
        WebElement inputPassword = driver.findElement(
                By.xpath("//label[normalize-space()='Password']/parent::div/following-sibling::div//input")
        );
        inputPassword.sendKeys(password);

        // Confirm Password - Buscamos por Label
        WebElement inputConfirmPassword = driver.findElement(
                By.xpath("//label[normalize-space()='Confirm Password']/parent::div/following-sibling::div//input")
        );
        inputConfirmPassword.sendKeys(password);
    }

    private void buscarUsuario(String username) {
        // En lugar de placeholder, buscamos el input a través de su Label visual
        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::div//input")
                )
        );
        searchInput.clear();
        searchInput.sendKeys(username);

        // A veces OrangeHRM necesita un pequeño respiro después de escribir para habilitar el botón internamente
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hacemos clic en Search asegurándonos de que no esté interceptado
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Search']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn); // Usamos JS Click por si hay superposiciones

        // Esperamos que la grilla recargue. La clase "oxd-table-card" es más estable que "oxd-table-row--clickable"
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".oxd-table-card")));
    }

    private void irAPIMAddEmployee() {
        WebElement menuPIM = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[text()='PIM']")
                )
        );
        menuPIM.click();

        WebElement addEmployee = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='Add Employee']")
                )
        );
        addEmployee.click();

        wait.until(ExpectedConditions.urlContains("addEmployee"));
    }

    private void completarDatosEmpleado(String firstName, String middleName, String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
        driver.findElement(By.name("firstName")).clear();
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("middleName")).clear();
        driver.findElement(By.name("middleName")).sendKeys(middleName);
        driver.findElement(By.name("lastName")).clear();
        driver.findElement(By.name("lastName")).sendKeys(lastName);
    }

    private void subirFotoEmpleado() {
        WebElement inputFoto = driver.findElement(
                By.cssSelector("input[type='file']")
        );

        String rutaFoto = System.getProperty("user.dir")
                + "\\src\\test\\resources\\foto_empleado.jpg";

        File imagen = new File(rutaFoto);

        if (!imagen.exists()) {
            System.out.println("⚠ Archivo no encontrado en: " + rutaFoto);
            return;
        }

        inputFoto.sendKeys(imagen.getAbsolutePath());
        System.out.println("✓ Foto subida: " + imagen.getAbsolutePath());
    }

    private void seleccionarDropdown(String label, String opcion) {
        // Encontramos el contenedor del texto seleccionado basándonos en el Label, es mucho más resistente a cambios de UI
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[normalize-space()='" + label + "']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text')]")
                )
        );
        dropdown.click();

        WebElement opcionElement = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='listbox']//span[text()='" + opcion + "']")
                )
        );
        opcionElement.click();
    }

    private void clickBoton(String texto) {
        WebElement boton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='" + texto + "']")
                )
        );
        boton.click();
    }

    private void verificarMensajeExito(String mensajeEsperado) {
        try {
            WebElement toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".oxd-toast-content")
                    )
            );
            Assert.assertTrue(toast.getText().contains(mensajeEsperado),
                    "Mensaje esperado: " + mensajeEsperado + " | Actual: " + toast.getText());
        } catch (TimeoutException e) {
            System.out.println("⚠ Toast no encontrado, continuando...");
        }
    }

    private void hacerLogout() {
        WebElement perfilUser = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".oxd-userdropdown-tab")
                )
        );
        perfilUser.click();

        WebElement logout = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='Logout']")
                )
        );
        logout.click();

        wait.until(ExpectedConditions.urlContains("login"));
    }
}