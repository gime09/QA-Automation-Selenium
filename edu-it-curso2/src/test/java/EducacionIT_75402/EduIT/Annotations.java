package EducacionIT_75402.EduIT;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Annotations {
	@Test
	public void testCase1() {
		System.out.println("Este es el caso de prueba normal 1 "); 
	}
	@Test
	public void testCase2() {
		System.out.println("Este es el caso de prueba normal 2"); 
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Esto se ejecutara antes de cada metodo");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Esto se ejecutara despues de cada metodo");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Esto se ejecutara antes de la Clase");
	}

	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Esto se ejecutara antes del Test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Esto se ejecutara despues del Test");
	}
	@AfterClass
	public void afterClass() {
		System.out.println("Esto se ejecutara despues de la Clase");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Esto se ejecutara antes de la Suite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Esto se ejecutara despues de la Suite");

	}



}