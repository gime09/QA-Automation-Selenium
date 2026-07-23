
package paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddUser {
    @FindBy(name="systemUser[userType]")
    WebElement selUserRole;

    @FindBy(id="systemUser_employeeName_empName")
    WebElement txtEmployeeName;

    @FindBy(xpath="//input[@id='systemUser_userName']")
    WebElement txtUsername;

    @FindBy(css="#systemUser_status")
    WebElement selStatus;

    @FindBy(name="systemUser[password]")
    WebElement txtPassword;

    @FindBy(id="systemUser_confirmPassword")
    WebElement txtConfirmPassword;

    @FindBy(id="btnSave")
    WebElement btnSave;

    WebDriver driver;

    public AddUser(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void completeAddUserForm(String userRole, String employeeName, String userName, String status, String password, String confirmPassword) {
        Select varUserRole = new Select(selUserRole);
        varUserRole.selectByVisibleText(userRole);

        txtEmployeeName.sendKeys(employeeName);
        txtUsername.sendKeys(userName);

        Select varStatus = new Select(selStatus);
        varStatus.selectByVisibleText(status);

        txtPassword.sendKeys(password);
        txtConfirmPassword.sendKeys(confirmPassword);
    }

    public void clicOnSave() {
        btnSave.click();
    }
}
