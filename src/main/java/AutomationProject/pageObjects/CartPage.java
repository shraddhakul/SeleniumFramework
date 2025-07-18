package AutomationProject.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import AutomationProject.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

    // Remove local driver variable

    public CartPage(WebDriver driver) {
        super(driver);
        // No need to call PageFactory.initElements here if AbstractComponent already does it
    }

    // Locators
    @FindBy(xpath = "//div[@class='cartSection']/h3")
    private List<WebElement> selectedProducts;

    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement checkOut;

    // Return list of selected products
    public List<WebElement> getSelectedProductList() {
        return selectedProducts;
    }

    // Check if the expected product exists in the cart
    public boolean isProductMatch(String productName) {
        return selectedProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }

    // Proceed to checkout and return OrderDashboard page object
    public OrderDashBoard checkout() {
        waitForElementToClickable(checkOut);
        Actions actions = new Actions(driver);
        actions.moveToElement(checkOut).click().build().perform();
        return new OrderDashBoard(driver); // Use the passed driver (thread-safe)
    }
}
