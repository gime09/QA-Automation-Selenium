package EducacionIT_75402.EduIT;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PracticaM3 {

	//Driver

	WebDriver driver;

	//Variables que necesitaremos para nuestra prueba
	String url="http://www.automationpractice.pl/";
	String urlAuthentication="http://www.automationpractice.pl/index.php?controller=my-account";

	@BeforeSuite
	public void setUP() {
		//Paso 1: Definir que navegador vamos a utilizar
		driver= new EdgeDriver();

	}
	@BeforeTest
	public void irUrl() {
		//Paso 2:  Abrir la página que se va a probar
		driver.get(url);

	}

	@BeforeClass
	public  void maxVentana() {
		driver.manage().window().maximize();//Maximiza la ventana

	}

	@Test
	public void registrarUsuario() {
		//Paso 3: Hacer click en "Sign in"

		WebElement lnkSign=driver.findElement(By.partialLinkText("Sign"));
		lnkSign.click();

		//Paso 4: Escribir el correo electronico

		WebElement txtEmail=driver.findElement(By.cssSelector("#email_create"));
		txtEmail.sendKeys("correo04feb"+Math.random()+"@gmail.com");

		//Paso 5: Hacer click en el boton "Create an Account"

		WebElement btnCreate=driver.findElement(By.id("SubmitCreate"));
		btnCreate.click();

		//Paso 6: Necesitamos  agregar una espera de X tiempo o hasta que el radio button se cargue

		WebDriverWait espera=new WebDriverWait(driver,Duration.ofSeconds(10));
		espera.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1")));

		//Seleccionar Sr. o Sra
		WebElement radTitulo=driver.findElement(By.id("id_gender1"));
		radTitulo.click();

		//Paso 7: Escribir el nombre

		WebElement  txtNombre=driver.findElement(By.name("customer_firstname"));
		txtNombre.sendKeys("Arturo");

		//Paso 8: Escribir el apellido

		WebElement  txtApellido=driver.findElement(By.cssSelector("#customer_lastname"));
		txtApellido.sendKeys("Lopez");

		//Paso 9: Limpiar el valor del correo electronico
		WebElement txtEmailForm=driver.findElement(By.id("email"));
		txtEmailForm.clear();

		//Paso 10: Escribir un nuevo valor de correo electronico
		txtEmailForm.sendKeys("correoModificado04feb"+Math.random()+"@gmail.com");

		//Paso 11: Escribir la contraseña
		WebElement  txtPassword=driver.findElement(By.name("passwd"));
		txtPassword.sendKeys("123456");

		//Paso 12: Seleccionar la fecha de nacimiento(dia-mes-año)

		Select drpDaysForm=new Select(driver.findElement(By.id("days")));
		drpDaysForm.selectByValue("11");

		Select drpMonthsForm=new Select(driver.findElement(By.id("months")));
		drpMonthsForm.selectByValue("4");

		Select drpYearsForm=new Select(driver.findElement(By.id("years")));
		drpYearsForm.selectByValue("1998");

		//Paso 13: Hacer click en el checkbox

		WebElement chkNewsletter=driver.findElement(By.name("newsletter"));
		chkNewsletter.click();

		//Paso 14: Hacer clic en boton 'Register'
		WebElement  btnRegister=driver.findElement(By.xpath("//*[text()='Register']"));
		btnRegister.click();
		
		//Ver por Console el contenido de la variable
		System.out.print("Resultado esperado:" + urlAuthentication);
		System.out.print("########################################");
		System.out.print("Resultado obtenido:" + driver.getCurrentUrl());
		
		//(18) Confirmacion de la cuenta 
		Assert.assertEquals(urlAuthentication,driver.getCurrentUrl());
       
		
	}
	
	@AfterMethod
	public void CapturaPantalla() throws IOException {
		//(19) Captura de Pantalla
		File Screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Screen, new File("..\\EduIT\\Evidencias\\pantalla_"+System.currentTimeMillis()+".png"));
		
	}

	//(15) fin de prueba
	@AfterClass
	public void finPrueba() {

		System.out.println("-------------");
		System.out.println("fin de prueba");
		System.out.println("-------------");

	}

	//(16) Cerrar Navegador
	@AfterTest
	public void cierreNavegador() {

		driver.quit();

	}
	//(17) fin de suite
	@AfterSuite
	public void finSuite () {

		System.out.println("-------------");
		System.out.println("fin de Suite");
		System.out.println("-------------");

	}


}
