package AutomationProject.tests.testComponents;



import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            count++;
            System.out.println("[Retry Analyzer] Retrying: " + result.getMethod().getMethodName() + " | Attempt " + count);
            result.setAttribute("RETRY_COUNT", count);  // Store retry count
            return true;
        }
        return false;
    }
}
