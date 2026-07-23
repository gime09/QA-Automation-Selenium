package paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PaginaLogin {
	@FindBy(id="email_create")
	WebElement txtEmail;
	
	@FindBy(id="SubmitCreate")
	WebElement btnCreate;
	
	@FindBy(xpath="//h1[contains(text(),'Authentication')]")
	WebElement titleForm;
	
	@FindBy(xpath="//header/div[3]/div[1]/div[1]/div[1]/a[1]")
	WebElement imgInicio;
	
	public PaginaLogin(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
		btnCreate.click();
	}
	
	public String getTextTitleForm() {
		return titleForm.getText();
	}
	
	public void gotoInicio() {
		imgInicio.click();
	}
}
