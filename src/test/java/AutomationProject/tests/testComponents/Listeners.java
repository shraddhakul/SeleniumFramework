package AutomationProject.tests.testComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();

        if (driver == null) {
            System.out.println("❌ WebDriver not found. Skipping screenshot.");
            return;
        }

        try {
            // 1. Take screenshot
            File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 2. Create directory if not exists
            File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
            if (!screenshotDir.exists() && !screenshotDir.mkdirs()) {
                System.err.println("⚠️ Failed to create screenshot directory.");
                return;
            }

            // 3. Generate file name
            String methodName = result.getMethod().getMethodName();
            String fileName = methodName + "_" + System.currentTimeMillis() + ".png";
            File destFile = new File(screenshotDir, fileName);

            // 4. Copy file
            FileUtils.copyFile(srcScreenshot, destFile);
            System.out.println("✅ Screenshot saved at: " + destFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // other ITestListener methods (optional overrides)
}
