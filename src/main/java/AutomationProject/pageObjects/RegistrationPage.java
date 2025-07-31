package AutomationProject.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

    WebDriver driver;
    @FindBy(css = "a.btn1[routerlink='/auth/register']")
    private WebElement registerLinkButton;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userMobile")
    private WebElement phoneInput;

    @FindBy(css = "select[formcontrolname='occupation']")
    private WebElement occupationSelect;

    @FindBy(css = "input[type='radio'][value='Male']")
    private WebElement genderMaleRadio;

    @FindBy(css = "input[type='radio'][value='Female']")
    private WebElement genderFemaleRadio;

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(css = "input[type='checkbox'][formcontrolname='required']")
    private WebElement ageCheckbox;

    @FindBy(id = "login")
    private WebElement registerButton;

 @FindBy(xpath = "//h1[contains(@class, 'headcolor')]")
 private WebElement successMessage;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickRegisterLink() {
        registerLinkButton.click();
    }

    public void enterFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPhone(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void selectOccupation(String occupationVisibleText) {
        new Select(occupationSelect).selectByVisibleText(occupationVisibleText);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            genderMaleRadio.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            genderFemaleRadio.click();
        }
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public void checkAgeCheckbox() {
        if (!ageCheckbox.isSelected()) {
            ageCheckbox.click();
        }
    }

    // Java
    public void clickRegister() {
        try {
            registerButton.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // Fallback to JavaScript click if intercepted
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);
        }
    }


    public String getSuccessMessage() {
        return successMessage.getText();
    }
}