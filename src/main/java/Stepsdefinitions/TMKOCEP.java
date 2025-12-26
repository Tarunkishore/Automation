package Stepsdefinitions;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public void i_click_on(String string) throws IOException {
		System.out.println("Looking for search button : " + string);
		String searchTerm = Common.pageobjectVal(string);
		System.out.println("Found : " + string);
		driver.findElement(By.xpath(searchTerm)).click();
		System.out.println("Clicked " + string + " successfully");
		
		
	}

	@Then("I verify {string} available on present page")
	public void i_verify_available_on_present_page(String string) throws IOException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		String searchTerm = Common.pageobjectVal(string);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchTerm)));
		boolean b = driver.findElement(By.xpath(searchTerm)).isDisplayed();
		System.out.println(b+" I verify "+string+" available on present page ");
		WebElement element = driver.findElement(By.xpath(searchTerm));
		System.out.print("Element found: " + element.getText());

	}

	@And("I scoll to view {string}")
	public void i_scoll_to_view(String string) throws InterruptedException, IOException {
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		String searchTerm = Common.pageobjectVal(string);
		WebElement element = driver.findElement(By.xpath(searchTerm));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
//		System.out.println(js.executeScript("return window.pageYOffset;"));		// to get y-axis 
		System.out.println("scrolled successfully to : " + string);
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchTerm)));
		System.out.println("Scrolled Successfully to : " + element.getText());

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
