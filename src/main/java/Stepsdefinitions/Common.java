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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;

public class Common {
	public static WebDriver driver;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static String destPath;
	
//	@BeforeSuite
	public static void extentSparkReport() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-hh-mm-ss-ms");
//		String filepath = System.getProperty("user.dir")+"/extent-reports/"+sdf.format(new Date())+".html";
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
//		extentTest = extentReports.createTest("Test of Extent Spark Reports");

	}
	@AfterStep
	public static String getScreenshotPath() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String timestamp = new SimpleDateFormat("dd-hh-mm-ss-ms").format(new Date());
		File source = ts.getScreenshotAs(OutputType.FILE);
		destPath = System.getProperty("user.dir") + "/screenshots/" + timestamp +".png";
		File file = new File(destPath);
		FileUtils.copyFile(source, file);
		return destPath;
	}
	
	
//	public static void addScreenshot(Scenario scenario) {
//		final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//		scenario.attach(screenshot, "image/png", scenario.getName());
//	}

//	@AfterStep
//   public void afterStep() throws IOException{
//		Common.getScreenshotPath();
//		Common.extentSparkReport();
//        if (scenario.isFailed()) {
//                String screenshotPath = Common.getScreenshotPath();
//                extentReports.attachScreenshot(screenshotPath);  // Attach the screenshot to ExtentReports
//		extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
//            
//        }
//    }

//	// Create a new test in ExtentReports
//	public static void createTest(String testName) {
//		testLog = reports.createTest(testName); // Create a test with the given name
//	}
//
//	// Log each test step into the report
//	public static void logTestStep(String stepDescription) {
//		testLog.info(stepDescription); // Log information about the test step
//	}
//
//	// Attach a screenshot to the ExtentReports report
//	public static void attachScreenshot(String screenshotPath) {
//		testLog.addScreenCaptureFromPath(screenshotPath); // Attach the screenshot to the report
//	}

	@Given("Launch Brave Browser")
	public void launch_brave_browser() throws InterruptedException, IOException {
//		Common.extentSparkReport();
//		String browserPath = "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser";
		ChromeOptions options = new ChromeOptions();
//		options.setBinary(browserPath);
		
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
//		Common.takeScreenshot();
		System.out.println("Browser launched Successfully");
//		screenshot(driver, System.currentTimeMillis());
		System.out.println("Looking for clearCacheCookes");
		driver.manage().deleteAllCookies();
		Thread.sleep(7000);
		System.out.println("Successfully clearCacheCookes");

	}

//	@BeforeStep
//	public void beforeStep() {
//		Common.extentSparkReport();
//	}

//	@AfterMethod
//	public static void afterMethod(ITestResult result) {
//		if (result.getStatus() == ITestResult.SUCCESS) {
//			testLog.log(Status.PASS, "This is " + result.getMethod().getMethodName());
//		} else if (result.getStatus() == ITestResult.FAILURE) {
//			testLog.log(Status.FAIL, "This is " + result.getMethod().getMethodName());
//		} else if (result.getStatus() == ITestResult.SKIP) {
//			testLog.log(Status.SKIP, "This is " + result.getMethod().getMethodName());
//		}
//	}

	@After
	public void tearDown() {
//		extentReports.flush();
		driver.quit();
	}
}

//how to implement takescreenshot and attach it in ExtentSparkReporter report of corresponding test case steps of feature file in BDD selenium java