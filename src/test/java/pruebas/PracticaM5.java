package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import paginas.PaginaLogin;



public class PracticaM5 {
	String url="http://www.automationpractice.pl/index.php?controller=authentication&back=my-account";
	WebDriver driver;
	PaginaLogin login;
	
	@BeforeMethod
	public void setUP() {
		driver=new EdgeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	@Test(dataProvider="Datos Login")
	public void loginUser(String email,String password){
		
		login= new PaginaLogin(driver);
		login.ingresarDatos(email,password);
		Assert.assertEquals(login.getTitleForm(),"AUTHENTICATION");
		
	}
	
	@DataProvider(name="Datos Login")
	public Object[][]getData(){
		
		Object[][] data= new Object[3][2];
		
		data[0][0]="test1@gmail.com";data[0][1]="1230";
		data[1][0]="test2@gmail.com";data[1][1]="4560";
		data[2][0]="test3@gmail.com";data[2][1]="7890";
		
		
		return data;
	}
   
	@AfterMethod
	public void tearDown() {	
		driver.quit();
		
	}

}
