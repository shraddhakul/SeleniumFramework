package AutomationProject.utils;

	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;

	import java.io.File;
	import java.io.IOException;
	import java.nio.file.Files;

	public class ScreenshotUtils {

	    public static String getScreenShot(String testName, WebDriver driver) throws IOException {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);

	        // Make sure directory exists
	        File destDir = new File(System.getProperty("user.dir") + "/reports/");
	        if (!destDir.exists()) {
	            destDir.mkdirs();
	        }

	        File dest = new File(destDir, testName + ".png");
	        Files.copy(source.toPath(), dest.toPath());
	        return dest.getAbsolutePath();
	    }
	}



