package pruebas;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import paginas.AddUser;
import paginas.Home;
import paginas.Login;

public class Test_BonusTrack4b {
    WebDriver driver;
    File screen;
    String urlEsperada = "https://opensource-demo.orangehrmlive.com/index.php/admin/saveSystemUser";
    String chromeDriverPath = "..\\BonusTrack-OpenHRM\\Drivers\\chromedriver.exe";
    String firefoxDriverPath = "..\\BonusTrack-OpenHRM\\Drivers\\geckodriver.exe";

    @BeforeTest
    @Parameters("navegador")
    public void setUp(String navegador) throws Exception {
        System.out.println("Inicio de Suite de Pruebas");

        // Establecer la ubicación del driver
        if (navegador.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
        } else if (navegador.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            driver = new FirefoxDriver();
        }

        // Crear la instancia de Driver: abrir la página y maximizarla
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();

        screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\01_pantallaprincipal.png"));
    }

    @Test
    public void login() throws Exception {
        // Completar el formulario de login y hacer clic en el boton
        Login login = new Login(driver);
        login.enterCredentials("Admin", "admin123");

        screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\02_credencialesLogin.png"));

        login.clicOnLogin();

        screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\03_pantallahrm.png"));

        // Acceder a la opción de Registro de Usuario
        Home home = new Home(driver);
        home.goToAddUser();

        screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\04_formularioUsuario.png"));

        // Completar el formulario de registro de usuario
        AddUser addUser = new AddUser(driver);
        addUser.completeAddUserForm("Admin",
                "Enrique Lopez",
                "usuario0",
                "Enabled",
                "ABCdef123",
                "ABCdef123");

        screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\05_formularioLleno.png"));

        addUser.clicOnSave();

        Assert.assertEquals(urlEsperada, driver.getCurrentUrl());
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("Fin de Suite de Pruebas");
    }
}
