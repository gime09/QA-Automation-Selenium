package EducacionIT_75402.EduIT;


import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class PracticaM2 {

	//Variables que necesitaremos para nuestra prueba
	String url="http://www.automationpractice.pl/";

	@Test 
	public void registrarUsuario() {
		//Paso 1: Definir que navegador vamos a utilizar
		

		WebDriver driver= new EdgeDriver();

		driver.manage().deleteAllCookies();//Borra las cookies

		driver.manage().window().maximize();//Maximiza la ventana

		//Paso 2:  Abrir la página que se va a probar

		driver.get(url);

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
		
		//Paso 15: Cerrar el navegador
		driver.quit();
		

	}

}