package AutomationProject.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationProject.data.DataReader;
import AutomationProject.pageObjects.CartPage;
import AutomationProject.pageObjects.OrderDashBoard;
import AutomationProject.pageObjects.OrderHistoryPage;
import AutomationProject.pageObjects.ProductCatalogue;
import AutomationProject.tests.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

    private static final String EXPECTED_CONFIRM_MESSAGE = "THANKYOU FOR THE ORDER.";
    private static final String DEFAULT_EMAIL = "rutakul@gmail.com";
    private static final String DEFAULT_PASSWORD = "Rutakul@1";
    private static final String COUNTRY = "India";
    private static final String PRODUCT_NAME = "ZARA COAT 3";
    

    @Test(dataProvider = "getData", groups = "Purchase")
    public void generateOrder(HashMap<String, String> info) throws IOException, InterruptedException {
        ProductCatalogue productCatalogue = lp.appLogin(info.get("email"), info.get("passwd"));
        WebElement product = productCatalogue.getProductByName(info.get("product"));
        productCatalogue.addToCart(info.get("product"));

        CartPage cartPage = productCatalogue.goToCartPage();
        Assert.assertTrue(cartPage.isProductMatch(info.get("product")), "Product not found in cart");

        OrderDashBoard orderDashboard = cartPage.checkout();
        orderDashboard.completeOrder(COUNTRY);

        String confirmationMessage = orderDashboard.getConfirmMessage();
        Assert.assertEquals(confirmationMessage.toUpperCase(), EXPECTED_CONFIRM_MESSAGE, "Confirmation message mismatch");
    }

    @Test(dependsOnMethods = {"generateOrder"})
    public void orderHistoryValidation() throws InterruptedException  {
        ProductCatalogue productCatalogue = lp.appLogin(DEFAULT_EMAIL, DEFAULT_PASSWORD);
        OrderHistoryPage orderHistoryPage = productCatalogue.goToOrderHistory();
        
        Assert.assertTrue(orderHistoryPage.isOrderMatch(PRODUCT_NAME), "Order not found in history");
    }

    @Test(retryAnalyzer = AutomationProject.tests.testComponents.Retry.class)
    public void failedRetryValidation() {
        ProductCatalogue productCatalogue = lp.appLogin(DEFAULT_EMAIL, DEFAULT_PASSWORD);
        
        Assert.fail("Force failure for retry test");
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        DataReader dataReader = new DataReader();
        List<HashMap<String, String>> data = dataReader.getJsonDataToMap();
        return new Object[][] { { data.get(0) }, { data.get(1) } };
    }
}
