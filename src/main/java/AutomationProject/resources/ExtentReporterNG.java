package AutomationProject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporterNG {
    
    private static ExtentReports extent;

    public static synchronized ExtentReports getReportObject() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/reports/index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            
            reporter.config().setReportName("Automation Test Results");
            reporter.config().setDocumentTitle("Test Execution Report");
            
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "YourName");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
