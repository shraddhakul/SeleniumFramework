package AutomationProject.tests.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AutomationProject.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected LoginPage lp;  // Make it protected so subclasses can use

    public WebDriver initializeDriver() throws IOException {
    	System.out.println("Initializing driver...");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
            System.getProperty("user.dir") + "/src/main/java/AutomationProject/resources/GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : prop.getProperty("browser");

        WebDriver webDriver;
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.set(webDriver);

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void quitDriver() {
    	WebDriver driverInstance = getDriver();
        if (driverInstance != null) {
            driverInstance.quit();
            driver.remove();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        initializeDriver();
        lp = new LoginPage(getDriver());
        lp.goTo("https://rahulshettyacademy.com/client");  // Launch URL here
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        quitDriver();
    }
}
