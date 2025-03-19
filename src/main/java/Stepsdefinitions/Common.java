package Stepsdefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import  io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class Common {
	public static WebDriver driver;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static String destPath;
	ChromeOptions options = new ChromeOptions();
	
	public static void extentSparkReport() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-hh-mm-ss-ms");
		extentReports = new ExtentReports();
		String filepath = System.getProperty("user.dir") + "/extent-reports/"+ sdf.format(new Date()) + ".html";
		sparkReporter = new ExtentSparkReporter(filepath);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TMKOC Report Name");
		sparkReporter.config().setDocumentTitle("MyReportTitle");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Tester", "SQA TARUN");
		extentReports.setSystemInfo("Operationg System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("os.version"));

	}
	
//	@AfterStep(order=0)
	public static String getScreenshotPath() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String timestamp = new SimpleDateFormat("dd-hh-mm-ss-ms").format(new Date());
		File source = ts.getScreenshotAs(OutputType.FILE);
		destPath = System.getProperty("user.dir") + "/screenshots/" + timestamp +".png";
		File file = new File(destPath);
		FileUtils.copyFile(source, file);
		return destPath;
	}
	
//	@AfterStep(order=1)
	@AfterStep
	public void after(Scenario scenario) throws IOException {
		Common.extentSparkReport();
		extentTest = extentReports.createTest(scenario.getName());
		
		String screenshotPath = getScreenshotPath();
		
		if (scenario.isFailed()) {
			extentTest.generateLog(Status.FAIL, scenario.getName());
//            extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
            extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(Common.getScreenshotPath()).build());
        } else {
        	extentTest.generateLog(Status.PASS, scenario.getName());
//            extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
            extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(Common.getScreenshotPath()).build());
        }
	}

	@Given("Launch Brave Browser")
	public void launch_brave_browser() throws InterruptedException, IOException {
//		String browserPath = "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser";
//		String browserPath = "/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/test/resources/drivers/chromedriver/chromedriver";
//		System.setProperty("webdriver.chrome.driver", browserPath);
//		options.setBinary(browserPath);
		
//		options.addArguments("--headless");
//		options.addArguments("--window-size=1920x1080");
//		options.addArguments("--disable-gpu"); // For compatibility with some systems
//		options.addArguments("--remote-debugging-port=9222");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("Browser launched Successfully");
		System.out.println("Looking for clearCacheCookes");
		driver.manage().deleteAllCookies();
		Thread.sleep(7000);
		System.out.println("Successfully clearCacheCookes");

	}
	
	@After
	public void tearDown() throws IOException {		
		extentReports.flush();
		driver.quit();
	}
}

