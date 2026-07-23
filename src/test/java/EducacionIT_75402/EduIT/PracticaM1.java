package EducacionIT_75402.EduIT;
import org.junit.jupiter.api.Disabled;
//Librerias que se necesitaran para ejecutar este archivo
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PracticaM1 {
	
	//Variables que necesitaremos para nuestra prueba
	String url="http://www.automationpractice.pl/";
	
	@Test @Disabled
	public void lab1_test() {
		
		System.out.println("Hola Mundo de Automatización!!");
		
	}
	
	@Test @Disabled
	public void buscarProductoEdge() {
		
		//Paso 1: Definir que navegador vamos a utilizar
		
		WebDriver navegador= new EdgeDriver();
		
		navegador.manage().deleteAllCookies();//Borra las cookies
		
		navegador.manage().window().maximize();//Maximiza la ventana
		
		//Paso 2:  Abrir la página que se va a probar
		
		navegador.get(url);
		
		//Paso 3: Escribir la palabra que queremos buscar
		
		WebElement txtBusqueda=navegador.findElement(By.id("search_query_top"));
		txtBusqueda.sendKeys("dress");
		
		//Paso 4: Simular que presionamos ENTER
		
		txtBusqueda.sendKeys(Keys.ENTER);
		
		//Paso 5: Cerrar el navegador
		
		navegador.quit();//Cierra todas las pestañas que se trabajaron
		
	}
	
	@Test 
	public void buscarProductoFirefox() {
		
		//Paso 1: Definir que navegador vamos a utilizar
		
		WebDriver navegador= new FirefoxDriver();

		// ¡EL TRUCO SALVAVIDAS! Le pedimos a Selenium que espere hasta 10 segundos
		// si no encuentra un elemento antes de lanzar un error.
		navegador.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

		
		navegador.manage().deleteAllCookies();//Borra las cookies
		
		navegador.manage().window().maximize();//Maximiza la ventana
		
		//Paso 2:  Abrir la página que se va a probar
		
		navegador.get(url);
		
		//Paso 3: Escribir la palabra que queremos buscar
		
		WebElement txtBusqueda=navegador.findElement(By.id("search_query_top"));
		txtBusqueda.sendKeys("dress");
		
		//Paso 4: Simular que presionamos ENTER
		
		txtBusqueda.sendKeys(Keys.ENTER);
		
		//Paso 5: Cerrar el navegador
		
		navegador.quit();//Cierra todas las pestañas que se trabajaron
		
	}

}
