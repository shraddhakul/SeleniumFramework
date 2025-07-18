package AutomationProject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import AutomationProject.abstractComponents.AbstractComponent;

public class OrderDashBoard extends AbstractComponent {

    // No local driver needed since AbstractComponent already has it

    public OrderDashBoard(WebDriver driver) {
        super(driver);
        // No need to call PageFactory.initElements here; done in AbstractComponent
    }

    // PageFactory elements
    @FindBy(css = "[placeholder='Select Country']")
    private WebElement countrySelect;

    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement checkOut;

    @FindBy(xpath = "(//button[@type='button'])[2]")
    private WebElement countryMatch;

    @FindBy(css = ".action__submit")
    private WebElement placeOrderBtn;

    @FindBy(css = ".hero-primary")
    private WebElement confirmMsg;

    private final By countryDropdown = By.cssSelector(".ta-results");

    /**
     * Complete the order process by selecting a country and placing the order.
     * @param countryName the name of the country to select
     */
    public void completeOrder(String countryName) {
        Actions actions = new Actions(driver);
        actions.sendKeys(countrySelect, countryName).build().perform();

        // Scroll down to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Wait for country dropdown and select the matching country
        waitForElementToBeVisible(countryDropdown);
        countryMatch.click();

        // Wait until place order button is clickable and click it
        waitForElementToClickable(placeOrderBtn);
        placeOrderBtn.click();
    }

    /**
     * Get the confirmation message after order placement.
     * @return confirmation message text
     */
    public String getConfirmMessage() {
        return confirmMsg.getText();
    }
}
