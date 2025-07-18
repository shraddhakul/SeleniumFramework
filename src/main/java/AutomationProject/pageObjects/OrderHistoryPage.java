package AutomationProject.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationProject.abstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {

    // Constructor
    public OrderHistoryPage(WebDriver driver) {
        super(driver);

    }

    // Page Elements
    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> orderedProducts;

    // Business Logic

    /**
     * Returns the list of products in the order history.
     */
    public List<WebElement> getOrderedProductList() {
        return orderedProducts;
    }

    /**
     * Checks if the order history contains the specified product name.
     *
     * @param productName the product name to check
     * @return true if the product is found in the order history, else false
     */
    public boolean isOrderMatch(String productName) {

        return orderedProducts.stream()
            .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }
}
