package Stepsdefinitions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Stepsdefinitions.Common.PasswordManager;
import io.cucumber.java.*;
import io.cucumber.java.en.*;

public class Common {
	public static WebDriver driver;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static String destPath;
//	public static WebDriverWait wait;
	ChromeOptions options = new ChromeOptions();

	@Given("Launch Brave Browser")
	public void launch_brave_browser() throws InterruptedException, IOException {
//		String browserPath = "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser";
//		String browserPath1 = "/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/test/resources/drivers/chromedriver/chromedriver";
//		System.setProperty("webdriver.chrome.driver", browserPath1);
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

	public static void extentSparkReport() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-hh-mm-ss-ms");
		extentReports = new ExtentReports();
		String filepath = System.getProperty("user.dir") + "/extent-reports/" + sdf.format(new Date()) + ".html";
		sparkReporter = new ExtentSparkReporter(filepath);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TMKOC Report Name");
		sparkReporter.config().setDocumentTitle("MyReportTitle");
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Tester", "SQA TARUN");
		extentReports.setSystemInfo("Operationg System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("os.version"));

	}

	public static String getScreenshotPath() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String timestamp = new SimpleDateFormat("dd-hh-mm-ss-ms").format(new Date());
		File source = ts.getScreenshotAs(OutputType.FILE);
		destPath = System.getProperty("user.dir") + "/screenshots/" + timestamp + ".png";
		File file = new File(destPath);
		FileUtils.copyFile(source, file);
		return destPath;
	}

	public static String pageobjectVal(String string) throws IOException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/main/resources/Pageobjects/pageobject.properties";
		String pageObjectPath = projectPath + path;
		FileReader reader2 = new FileReader(pageObjectPath);
		
//		FileReader reader2 = new FileReader(
//				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop2 = new Properties();
		prop2.load(reader2);
		String searchTerm = prop2.getProperty(string);
		return searchTerm;
	}

	public static String configVal(String string) throws IOException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/main/resources/config/configuration.properties";
		String configPath = projectPath + path;
		FileReader reader = new FileReader(configPath);
//		FileReader reader = new FileReader(
//				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/config/configuration.properties");
		Properties prop = new Properties();
		prop.load(reader);
		String searchTerm = prop.getProperty(string);
		return searchTerm;
	}

	@AfterStep
	public void after(Scenario scenario) throws IOException {
		Common.extentSparkReport();
		extentTest = extentReports.createTest(scenario.getName());

//		String screenshotPath = getScreenshotPath();
		getScreenshotPath();

		if (scenario.isFailed()) {
			extentTest.generateLog(Status.FAIL, scenario.getName());
//            extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
			extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(Common.getScreenshotPath()).build());
		} else {
			extentTest.generateLog(Status.PASS, scenario.getName());
//            extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
			extentTest.log(Status.FAIL,
					MediaEntityBuilder.createScreenCaptureFromPath(Common.getScreenshotPath()).build());
		}
	}

	@Then("I click {string} times on {string}")
	public void i_click_times_on(String num, String string1) throws IOException {

		int applycount = Integer.parseInt(num);
		String searchTerm = Common.pageobjectVal(string1);
		Common.clickApply(applycount, searchTerm);

	}

	public static void clickApply(int num, String string) {
//		int count = 1;
		while (num >= 0) {
			WebElement button = driver.findElement(By.xpath(string));

			if (button.isEnabled()) {
			    button.click();
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(string)));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string)));
			} else {
			    System.out.println("Button is disabled and cannot be clicked.");
			}
			System.out.println("Apply count : " + num);
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(string)));
//			element.click();
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string))).click();
			
			num--;

		}
	}

	@When("I enter the {string} in {string}")
	public void i_enter_the_in(String InputData, String InputField) throws Exception {
		// String searchTerm = com.configVal(Password);
		if (InputData.equalsIgnoreCase("Password")) {
			String searchTerm = Common.pageobjectVal(InputField);
			System.out.println("Password:" + searchTerm + "  #ActualPassword:" + InputData);
			String password = PasswordManager.getDecodedPassword(InputData);

			driver.findElement(By.xpath(searchTerm)).sendKeys(password);
			// Thread.sleep(1000);

		} else {
			String searchTerm = Common.pageobjectVal(InputField);
			String searchTerm2 = Common.configVal(InputData);
			driver.findElement(By.xpath(searchTerm)).sendKeys(searchTerm2);
		}
	}

	public static class PasswordManager {

		// Base64 Decode the password
		public static String decodeBase64Password(String encodedPassword) throws Exception {
			try {
				byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
				return new String(decodedBytes);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Invalid Base64 encoded password: " + encodedPassword, e);
			}
		}

		// Fetch the Base64 encoded password from the configuration file and decode it
		public static String getDecodedPassword(String encodedPasswordKey) throws Exception {
			// Load the properties file
			String searchTerm2 = Common.configVal(encodedPasswordKey);

			// Fetch the Base64 encoded password from the properties file
//			String encodedPassword = prop.getProperty(encodedPasswordKey);
			System.out.println("Password:" + encodedPasswordKey + "  #ActualPassword:" + searchTerm2);
			// Decode the password and return it
			return decodeBase64Password(searchTerm2);
		}
	}

	@After
	public void tearDown() throws IOException {
		extentReports.flush();
		driver.quit();
	}
}
