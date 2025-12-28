package Stepsdefinitions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Common {
	public static WebDriver driver;
	public static ExtentSparkReporter sparkReporter;	// UI of the report
	public static ExtentReports extentReports;		// populate common info of the report
	public static ExtentTest extentTest;		// create test case entries in the report and update status of the test methods
	//	public static String destPath;
	public static WebDriverWait wait;
	ChromeOptions options = new ChromeOptions();

	@Given("Launch Brave Browser")
	public void launch_brave_browser() throws InterruptedException, IOException {

		//		String browserPath = "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser";
		//		String browserPath1 = "/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/test/resources/drivers/chromedriver/chromedriver";
		//		System.setProperty("webdriver.chrome.driver", browserPath1);
		//		options.setBinary(browserPath);

		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});	// to remove "chrome is being controlled by automated Software"
		options.addArguments("--incognito");
		//		options.addArguments("--headless=new");
		//		options.addArguments("--window-size=1920x1080");
		//		options.addArguments("--disable-gpu"); // For compatibility with some systems
		//		options.addArguments("--remote-debugging-port=9222");

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		System.out.println("Browser launched Successfully");
		System.out.println("Looking for clearCacheCookes");
		driver.manage().deleteAllCookies();
		System.out.println("Successfully clearCacheCookes");

	}

	public static void extentSparkReport() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
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
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms").format(new Date());
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir") + "/screenshots/" + timestamp + ".png";
		File file = new File(destPath);
		FileUtils.copyFile(source, file);
		return destPath;
	}

	public static String pageobjectVal(String string) throws IOException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/main/resources/Pageobjects/pageobject.properties";
		String pageObjectPath = projectPath + path;
		FileReader reader2 = new FileReader(pageObjectPath);
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
		Properties prop = new Properties();
		prop.load(reader);
		String searchTerm = prop.getProperty(string);
		return searchTerm;
	}

	//	 commented to not generate screenshot and extent report for now do not delete below line
	@AfterStep
	public void after(Scenario scenario) throws IOException {
		Common.extentSparkReport();
		extentTest = extentReports.createTest(scenario.getName());
		//		getScreenshotPath();

		if (scenario.isFailed()) {
			extentTest.generateLog(Status.FAIL, scenario.getName());
			//			extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(Common.getScreenshotPath()).build());
			extentTest.log(Status.FAIL, scenario.getName());
		} else {
			extentTest.generateLog(Status.PASS, scenario.getName());
			extentTest.log(Status.PASS, scenario.getName());
			//			extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Common.getScreenshotPath()).build());

		}		
	}

	@Then("I click {string} job is available")
	public void i_click_job_is_available(String string) throws IOException {
		int count = 0;
		String searchTerm = Common.pageobjectVal(string);
		WebElement button = driver.findElement(By.xpath(searchTerm));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchTerm)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchTerm)));
		if(button.isEnabled()) {
			while(button.isEnabled()) {
				button.click();
				count =+ 1;
				System.out.println("Apply count : " + count);
				wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchTerm)));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchTerm)));
			}
		}
		else {
			System.out.println("Job not found or Apply button is disabled.");
		}

		System.out.println("Clicked Apply Job button "+count+ " times.");
	}

	@Then("I click {string} times on {string}")
	public void i_click_times_on(String num, String string1) throws IOException {

		int applycount = Integer.parseInt(num);
		String searchTerm = Common.pageobjectVal(string1);
		Common.clickApply(applycount, searchTerm);

	}

	public static void clickApply(int num, String string) {
		for(int i=1; i<=num; i++) {
			WebElement button = driver.findElement(By.xpath(string));
			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(string)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string)));

			if (button.isEnabled()) {
				button.click();

			} else {
				System.out.println("Button is disabled and cannot be clicked.");
			}
			System.out.println("Apply count : " + i);
		}
	}

	@And("I select {string}")
	public void i_select(String string) throws IOException {
		Common.pageobjectVal(string);
		List<WebElement> checkboxes=driver.findElements(By.xpath(string));
		for(WebElement checkbox : checkboxes) {
			checkbox.click();
		}

	}

	@When("I enter the {string} in {string}")
	public void i_enter_the_in(String InputData, String InputField) throws Exception  {
		// String searchTerm = com.configVal(Password);
		Thread.sleep(1000);
		if (InputData.equalsIgnoreCase("Password")) {
			String searchTerm = Common.pageobjectVal(InputField);
			System.out.println("Password:" + searchTerm + "  #ActualPassword:" + InputData);
			String password = PasswordManager.getDecodedPassword(InputData);

			driver.findElement(By.xpath(searchTerm)).sendKeys(password);
			Thread.sleep(1000);

		} else {
			String searchTerm = Common.pageobjectVal(InputField);
			String searchTerm2 = Common.configVal(InputData);
			Thread.sleep(1000);
			driver.findElement(By.xpath(searchTerm)).sendKeys(searchTerm2);
			Thread.sleep(1000);

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
		extentReports.flush();		// commented do not delete 
		driver.quit();
	}
}
