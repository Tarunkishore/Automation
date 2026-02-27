package Stepsdefinitions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.*;
import io.cucumber.java.en.*;


public class Common {
	public static WebDriver driver;
	public static ExtentSparkReporter sparkReporter;	// UI of the report
	public static ExtentReports extentReports;		// populate common info of the report
	public static ExtentTest extentTest;		// create test case entries in the report and update status of the test methods
	public static ThreadLocal<ExtentTest> scenarioExtentTest = new ThreadLocal<>();
	public static ThreadLocal<Integer> stepCounter = ThreadLocal.withInitial(() -> 0);
	public static String reportFilePath;
	public static String reportDirectoryPath;
	//	public static String destPath;
	public static WebDriverWait wait;
	public static String browserName;
	public static boolean headless;
	public static boolean incognito;

	public static void setup(String browser, String head, String incog) {
		browserName = browser;
		headless = Boolean.parseBoolean(head);
		incognito = Boolean.parseBoolean(incog);
	}

//	@parameter({"browser"})
	@Given("Launch Brave Browser")
	public void launch_brave_browser() throws InterruptedException, IOException {

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});	// to remove "chrome is being controlled by automated Software"
			if (incognito) {
				options.addArguments("--incognito");
			}
			if (headless) {
				options.addArguments("--headless=new");
			}
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (incognito) {
				options.addArguments("-private");
			}
			if (headless) {
				options.addArguments("-headless");
			}
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
			if (incognito) {
				options.addArguments("--inprivate");
			}
			if (headless) {
				options.addArguments("--headless=new");
			}
			driver = new EdgeDriver(options);
		} else {
			// Default to Chrome if no match
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
			if (incognito) {
				options.addArguments("--incognito");
			}
			if (headless) {
				options.addArguments("--headless=new");
			}
			driver = new ChromeDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		System.out.println("Browser launched Successfully");
		System.out.println("Looking for clearCacheCookes");
		driver.manage().deleteAllCookies();
		System.out.println("Successfully clearCacheCookes");
	}

	public static synchronized void extentSparkReport() {
		if (extentReports != null) {
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
		extentReports = new ExtentReports();
		String reportDir = System.getProperty("user.dir") + "/report/";
		try {
			Files.createDirectories(Path.of(reportDir));
		} catch (IOException e) {
			throw new RuntimeException("Unable to create report directory: " + reportDir, e);
		}
		reportDirectoryPath = reportDir;
		reportFilePath = reportDir + sdf.format(new Date()) + ".html";
		sparkReporter = new ExtentSparkReporter(reportFilePath);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TMKOC Report Name");
		sparkReporter.config().setDocumentTitle("MyReportTitle");
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Tester", "SQA TARUN");
		extentReports.setSystemInfo("Operationg System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("os.version"));
	}

	public static String getScreenshotPath() throws IOException {
		if (driver == null) {
			return null;
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms").format(new Date());
		File source = ts.getScreenshotAs(OutputType.FILE);
		String baseReportDir = reportDirectoryPath != null ? reportDirectoryPath : System.getProperty("user.dir") + "/report/";
		String screenshotDir = baseReportDir + "screenshot/";
		Files.createDirectories(Path.of(screenshotDir));
		String destPath = screenshotDir + "screenshot-" + timestamp + ".png";
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

	@Before
	public void beforeScenario(Scenario scenario) {
		Common.extentSparkReport();
		extentTest = extentReports.createTest(scenario.getName());
		scenarioExtentTest.set(extentTest);
		stepCounter.set(0);
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		ExtentTest currentTest = scenarioExtentTest.get();
		if (currentTest == null) {
			return;
		}
		int currentStep = stepCounter.get() + 1;
		stepCounter.set(currentStep);
		String stepLabel = CucumberStepListener.getCurrentStep();
		if (stepLabel == null || stepLabel.isBlank()) {
			stepLabel = "Step " + currentStep;
		}
		Status stepStatus = mapToExtentStatus(scenario);
		String screenshotPath = null;
		try {
			screenshotPath = Common.getScreenshotPath();
		} catch (Exception e) {
			currentTest.log(stepStatus, stepLabel + " - Screenshot capture failed: " + e.getMessage());
		}
		if (screenshotPath != null) {
			currentTest.log(stepStatus, stepLabel,
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else {
			currentTest.log(stepStatus, stepLabel + " - Screenshot not available");
		}
	}

	private Status mapToExtentStatus(Scenario scenario) {
		String cucumberStatus = scenario.getStatus().name();
		if ("FAILED".equalsIgnoreCase(cucumberStatus)) {
			return Status.FAIL;
		}
		if ("PASSED".equalsIgnoreCase(cucumberStatus)) {
			return Status.PASS;
		}
		return Status.WARNING;
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
		if (extentReports != null) {
			extentReports.flush();
		}
		CucumberStepListener.clear();
		scenarioExtentTest.remove();
		stepCounter.remove();
		if (driver != null) {
			driver.quit();
		}
	}
}
