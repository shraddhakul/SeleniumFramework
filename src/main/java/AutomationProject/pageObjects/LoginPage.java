package AutomationProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import AutomationProject.abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

    // No local driver needed — inherited from AbstractComponent

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        // No need to call PageFactory.initElements here; done in AbstractComponent
    }

    // Web Elements using PageFactory
    @FindBy(id = "userEmail")
    private WebElement userName;

    @FindBy(id = "userPassword")
    private WebElement passWord;

    @FindBy(id = "login")
    private WebElement submit;

    @FindBy(css = ".toast-error")
    private WebElement loginErrorToast;

    // Perform login and return ProductCatalogue page object
    public ProductCatalogue appLogin(String email, String password) {
        userName.sendKeys(email);
        passWord.sendKeys(password);
        submit.click();
        return new ProductCatalogue(driver);
    }

    // Retrieve toast error message displayed on login failure
    public String getToastErrorMsg() {
        waitForElementToBeVisible(loginErrorToast);
        return loginErrorToast.getText();
    }

    // Navigate to given URL
    public void goTo(String url) {
        driver.get(url);
    }
}
