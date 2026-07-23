package Edit.EducacionIt;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Laboratorio1 {

    @Test
    public void lab1_test() {
        System.out.println("Hola Mundo de Automatización!");
    }

    @Test
    public void lab1_e1() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");

        driver.close();
    }

    @Test
    public void lab1_e2() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://automationexercise.com/");

        driver.close();
    }

    @Test
    public void lab1_e3() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/products");

        WebElement txtBuscador = driver.findElement(By.id("search_product"));
        txtBuscador.click();

        driver.close();
    }
}