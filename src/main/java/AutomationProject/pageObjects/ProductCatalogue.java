package AutomationProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import AutomationProject.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

    // Constructor
    public ProductCatalogue(WebDriver driver) {
        super(driver);
    }

    // Page elements
    @FindBy(css = ".mb-3")
    private List<WebElement> products;

    @FindBy(css = ".ng-animating")
    private WebElement spinner;

    // Locators
    private By productContainer = By.cssSelector(".mb-3");
    private By addToCartButton = By.cssSelector(".card-body button:last-of-type");
    private By toastMessage = By.cssSelector("#toast-container");

    // Returns the list of products after waiting for visibility
    public List<WebElement> getProductList() {
        waitForElementToBeVisible(productContainer);
        return products;
    }

    // Finds and returns the product WebElement by its name
    public WebElement getProductByName(String productName) {
        return getProductList().stream()
            .filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
            .findFirst()
            .orElse(null);
    }

    // Adds the specified product to the cart
    public void addToCart(String productName) throws InterruptedException {
        WebElement product = getProductByName(productName);
        if (product != null) {
            product.findElement(addToCartButton).click();
            waitForElementToBeVisible(toastMessage);
            waitForElementToDisappear(spinner);
        } else {
            throw new RuntimeException("Product not found: " + productName);
        }
    }
}
