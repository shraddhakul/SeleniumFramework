package AutomationProject.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationProject.pageObjects.CartPage;
import AutomationProject.pageObjects.OrderHistoryPage;

public class AbstractComponent {
    protected WebDriver driver;  // Changed to protected

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // <-- Implicit wait added
        PageFactory.initElements(driver, this);
    }

    // Common header elements
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartIcon;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement myOrders;
    
    @FindBy(css=".table-bordered")
    WebElement orderHistoryTable;
    
    @FindBy(xpath="//tbody/tr[1]/td[5]/button[1]")
    WebElement viewBtn;

    By spinner = By.cssSelector(".ngx-spinner-overlay");

    public void waitForElementToBeVisible(By findBy) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToBeVisible(WebElement ele) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementToDisappear(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementToDisappear(By locator) throws InterruptedException {
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//            .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    	Thread.sleep(7000);
    }

    public void waitForPresenceOfElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(element));
    }

    public CartPage goToCartPage() throws InterruptedException {
        waitForElementToDisappear(spinner);
        cartIcon.click();
        return new CartPage(driver);
    }

    public OrderHistoryPage goToOrderHistory() throws InterruptedException {
    	waitForElementToDisappear(spinner);
        myOrders.click();
       waitForElementToBeVisible(viewBtn);
        return new OrderHistoryPage(driver);
    }
}
