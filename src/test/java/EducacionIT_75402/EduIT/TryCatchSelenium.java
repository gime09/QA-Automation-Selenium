package EducacionIT_75402.EduIT;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TryCatchSelenium {
	@Test
	public void testSeleniumExample() {
		WebDriver driver = null;
		String URL="http://www.automationpractice.pl/index.php?controller=authentication&back=my-account";

		By searchqueryLocator=By.cssSelector("input[placeholder='Search1']");

		try {

			driver= new EdgeDriver();		
			driver.get(URL);
			driver.manage().window().maximize();

			WebElement txtBuscador=driver.findElement(searchqueryLocator);

			txtBuscador.clear();

			txtBuscador.sendKeys("Blouse");

			txtBuscador.sendKeys(Keys.ENTER);




		} catch (Exception e) {

			//e.printStackTrace();
			System.out.println("Error:"+ e.getMessage());

			Assert.fail("Error: No Locate Element");
			

		} 

		finally {

			if (driver != null) {
				driver.quit();
			}


		}

	}
}



