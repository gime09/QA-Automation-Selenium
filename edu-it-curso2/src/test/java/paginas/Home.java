package paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
    @FindBy(id="menu_admin_viewAdminModule")
    WebElement lnkMenuAdmin;

    @FindBy(id="menu_admin_UserManagement")
    WebElement lnkMenuUsers;

    @FindBy(xpath="//a[@id='menu_admin_viewSystemUsers']")
    WebElement lnkMenuViewUsers;

    @FindBy(css="#btnAdd")
    WebElement btnAddUser;

    WebDriver driver;

    public Home(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goToAddUser() {
        lnkMenuAdmin.click();
        lnkMenuUsers.click();
        lnkMenuViewUsers.click();
        btnAddUser.click();
    }
}

