package AutomationProject.tests.testComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import AutomationProject.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Listeners implements ITestListener {

    private static ExtentReports extent = ExtentReporterNG.getReportObject();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();

        if (driver == null) {
            System.out.println("❌ WebDriver not found. Skipping screenshot.");
            test.get().fail(result.getThrowable());
            return;
        }

        try {
            // 1. Take screenshot
            File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 2. Create directory if not exists
            File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
            if (!screenshotDir.exists() && !screenshotDir.mkdirs()) {
                System.err.println("⚠️ Failed to create screenshot directory.");
                test.get().fail(result.getThrowable());
                return;
            }

            // 3. Generate file name
            String methodName = result.getMethod().getMethodName();
            String fileName = methodName + "_" + System.currentTimeMillis() + ".png";
            File destFile = new File(screenshotDir, fileName);

            // 4. Copy file
            FileUtils.copyFile(srcScreenshot, destFile);
            System.out.println("✅ Screenshot saved at: " + destFile.getAbsolutePath());

            // 5. Attach screenshot to report
            test.get().fail(result.getThrowable())
                    .addScreenCaptureFromPath(destFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
            test.get().fail(result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // other ITestListener methods (optional overrides)
}