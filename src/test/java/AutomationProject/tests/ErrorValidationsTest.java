package AutomationProject.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationProject.pageObjects.CartPage;
import AutomationProject.pageObjects.ProductCatalogue;
import AutomationProject.tests.testComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {
    
    private final String productName = "ADIDAS ORIGINAL";

    @Test(groups = {"ErrorHandling"})
    public void loginWithInvalidCredentialsShowsError() throws IOException {
        // Attempt login with invalid credentials
        lp.appLogin("rutakul@gmail.com", "Rutakul@123");
        
        // Validate error message on failed login
        String toastErrorMsg = lp.getToastErrorMsg();
        Assert.assertEquals(toastErrorMsg, "Incorrect email or password.");
    }
    
    @Test(groups = {"ErrorHandling"})
    public void cartDoesNotContainUnaddedProduct() throws InterruptedException, IOException {
        // Login with valid user
        ProductCatalogue pc = lp.appLogin("rohankul@gmail.com", "Iamking000");
        
        // Wait for products to load
        List<WebElement> allProducts = pc.getProductList();

        // Add specific product to cart
        pc.addToCart(productName);

        // Navigate to cart page
        CartPage cp = pc.goToCartPage();

        // Verify that a different product is NOT in the cart
        boolean isProductInCart = cp.isProductMatch("Zara Coat 2");
        Assert.assertFalse(isProductInCart, "Unexpected product found in cart.");
    }
}
