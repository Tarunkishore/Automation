package Stepsdefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TMKOCEP {

	WebDriver driver = Common.driver;
//	ExtentTest extentTest = Common.extentTest;

//	static {
//		String browserPath = "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser";
//		ChromeOptions options = new ChromeOptions();
//		options.setBinary(browserPath);
//		driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
//		
//	}

	@And("Search youtube and open sussessfully")
	public void search_youtube_and_open_sussessfully() throws IOException {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
//	      driver.get("https://www.youtube.com/");
		driver.get(prop.getProperty("URL_Youtube"));
//			Assert.assertEquals(driver.getTitle(), "YouTube");
//			screenshot(driver, System.currentTimeMillis());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		System.out.println("YouTube title verified successfully");
//			Common.takeScreenshot();
		
//		Common.extentTest.log(Status.PASS,"Search youtube and open sussessfully");
		 
		
	}

	@Then("I click on {string}")
	public void i_click_on(String string) throws IOException {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
		System.out.println("Looking for search button : " + string);
		String searchTerm = prop.getProperty(string);
		System.out.println("Found : " + string);
		driver.findElement(By.xpath(searchTerm)).click();
//			Common.takeScreenshot();
		System.out.println("Clicked " + string + " successfully");
//		extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
	}

	@Then("I verify {string} available on present page")
	public void i_verify_available_on_present_page(String string) throws IOException {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
		String searchTerm = prop.getProperty(string);
//	        System.out.println("Found : "+string);
		WebElement element = driver.findElement(By.xpath(searchTerm));
//	        Common.takeScreenshot();
		System.out.println("Element found: " + element.getText());
//		extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());

	}

	@And("I scoll to view {string}")
	public void i_scoll_to_view(String string) throws InterruptedException, IOException {
//			Thread.sleep(5000);
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		String searchTerm = prop.getProperty(string);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		WebElement element = driver.findElement(By.xpath(searchTerm));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
//			WebElement element = driver.findElement(By.xpath("//h6[contains(text(),'Education')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		System.out.println("scrolled successfully to : " + string);
		Thread.sleep(5000);
//			Common.takeScreenshot();
		System.out.println("Scrolled Successfully to : " + element.getText());
//		extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());

	}

	@When("Search {string}")
	public void search(String str) throws IOException {
		driver.findElement(By.xpath("(//input[@name='search_query'])[1]")).sendKeys(str);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
//			Common.takeScreenshot();
		System.out.println("TMKOC episode searched successfully");
//		extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
	}

	@Then("I enter {string} and {string}")
	public void i_enter_and(String string, String string2) {
		driver.findElement(By.xpath("//input[@id='usernameField']")).sendKeys(string);
		driver.findElement(By.xpath("//input[@id='passwordField']")).sendKeys(string2);
//		extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
	}

	@And("I wait Low")
	public void i_wait_low() throws InterruptedException {
		System.out.println("I wait for low");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}

	@And("I wait High")
	public void i_wait_high() throws InterruptedException {
		System.out.println("I wait for high");
		Thread.sleep(15000);
	}

//		@And("Close the Brave browser")
//		public void close_the_brave_browser() {
//			System.out.println("closed browser");
//			driver.quit();
//		}

	@When("Search foundit and open sussessfully")
	public void search_foundit_and_open_sussessfully() {
		driver.get("https://www.foundit.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}

	@Then("I click on foundit Login button")
	public void i_click_on_foundit_login_button() {
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}

	@Then("click on Login Via Password")
	public void click_on_login_via_password() {
		driver.findElement(By.xpath("//div[contains(text(),'Login via Password')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}

	@Then("I enter {string} and {string} of foundit")
	public void i_enter_and_of_foundit(String string, String string2) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.findElement(By.id("signInName")).sendKeys(string);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.findElement(By.id("password")).sendKeys(string2);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
	}

	@Then("I click on Profile Tab in foundit header")
	public void i_click_on_profile_tab_in_foundit_header() {
		driver.findElement(By.xpath("//img[@alt='TARUN KISHORE']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

		driver.findElement(By.xpath("//a[contains(text(),'View Profile')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}

	@Then("I click on replace resume")
	public void i_click_on_replace_resume() throws InterruptedException {
		String projectPath = System.getProperty("user.dir");
		System.out.println("ProjectFilePath : " + projectPath);
		Thread.sleep(10000);
		WebElement element = driver.findElement(By.xpath("(//h3[contains(text(),'Resume')])[1]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(10000);
		WebElement updateResumeButton = driver.findElement(By.xpath("(//input[@id='inline-resume'])[1]"));
		updateResumeButton.click();
		Thread.sleep(10000);
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys(
				"/Users/tarunkishore/git/repository/SeCuGhBDDTng/src/test/resources/Utilities/Tarunkishore_SQA.pdf");
		Thread.sleep(10000);

	}

	@Given("I switch to frame")
	public void i_switch_to_frame() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
	}
}
