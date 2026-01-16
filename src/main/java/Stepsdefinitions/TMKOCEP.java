package Stepsdefinitions;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.*;

public class TMKOCEP {

	WebDriver driver = Common.driver;
	ExtentTest extentTest = Common.extentTest;
	ExtentReports extentReports = Common.extentReports;
	WebDriverWait wait = Common.wait;

	@Then("I click on {string}")
	public void i_click_on(String string) throws IOException, InterruptedException {
		wait = new WebDriverWait(driver, (Duration.ofSeconds(10)));
		System.out.println("Looking for search button : " + string);
		String searchTerm = Common.pageobjectVal(string);
		System.out.println("Found : " + searchTerm);
		By locator = By.xpath(searchTerm);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;

	}
	
	@And("I will click on {string} or {string} only when it is down")
	public void i_will_click_on_or_only_when_it_is_up(String stringdown, String stringup) throws IOException {
		    String downXpath = Common.pageobjectVal(stringdown);
		    String upXpath = Common.pageobjectVal(stringup);
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    // If UP button is visible → do nothing
		    if (driver.findElements(By.xpath(upXpath)).size() > 0 &&
		        driver.findElement(By.xpath(upXpath)).isDisplayed()) {
		        System.out.println("Search Job button is already UP, hence not clicked");
		        return;
		    }

		    // If DOWN button is visible → click
		    if (driver.findElements(By.xpath(downXpath)).size() > 0) {
		        WebElement downBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(downXpath)));
		        downBtn.click();
		        System.out.println("Search Job DOWN button found and clicked");
		        return;
		    }
		
	}

	@Then("I verify {string} available on present page")
	public void i_verify_available_on_present_page(String string) throws IOException {
		String searchTerm = Common.pageobjectVal(string);
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchTerm)));
		boolean bool = driver.findElement(By.xpath(searchTerm)).isDisplayed();
		System.out.println(bool+" I verify "+string+" available on present page ");
		WebElement element = driver.findElement(By.xpath(searchTerm));
		System.out.println("Element found : --" + element.getText());

	}

	@And("I scoll to view {string}")
	public void i_scoll_to_view(String string) throws InterruptedException, IOException {
		String searchTerm = Common.pageobjectVal(string);		
		By locator = By.xpath(searchTerm);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Wait for element to be present
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		// Scroll to element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
		// Re-wait using LOCATOR (not element) to avoid stale reference
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		System.out.println("Scrolled successfully to : " + string);

	}

	@Then("I provide {string} in search box {string}")
	public void i_provide_in_search_box(String InputData, String InputField) throws IOException {		
		String inputData = InputData;		
		String searchTerm = Common.pageobjectVal(InputField);
		driver.findElement(By.xpath(searchTerm)).sendKeys(inputData);
		System.out.println("TMKOC episode searched successfully");

	}	

	@And("I wait {string}")
	public void i_wait(String string) throws InterruptedException, IOException {
		String searchTerm = Common.configVal(string);
		int num = Integer.parseInt(searchTerm);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(num));
	}
	
	@And("I wait for {string}")
	public void i_wait_for(String string) throws InterruptedException, IOException {
		String searchTerm = Common.configVal(string);
		int num = Integer.parseInt(searchTerm);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(num));
		Thread.sleep(4000);
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

	@And("I switch to frame")
	public void i_switch_to_frame() throws InterruptedException {
		//		Thread.sleep(2000);
		driver.switchTo().defaultContent();
	}
}
