package pruebas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PracticaM6 {
	String url="https://demo.guru99.com/test/upload/";
	WebDriver driver;

	@BeforeMethod
	public void setUP() {
		
        ChromeOptions options=new ChromeOptions();  
        //options.addArguments("--incognito");
        //options.addArguments("--headless");
		driver= new ChromeDriver(options);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	@Test
	public void CargarArchivo() {


		WebElement rutaArchivo=driver.findElement(By.id("uploadfile_0"));
        String filePath=System.getProperty("user.dir")+ "\\prueba.txt";
		System.out.println(filePath);
		rutaArchivo.sendKeys(filePath);
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

}
