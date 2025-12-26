package Stepsdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Instahyre {
	WebDriver driver = Common.driver;
	ExtentTest extentTest = Common.extentTest;
	ExtentReports extentReports = Common.extentReports;
	WebDriverWait wait = Common.wait;
	
	@When("Search Instahyre and open sussessfully")
	public void search_instahyre_and_open_sussessfully() {
		driver.get("https://www.instahyre.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		System.out.println("InstaHyre title verified successfully");
	}
	
	@Then("I enter {string} and {string} of instahyre")
	public void i_enter_and_of_instahyre(String string, String string2) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(string);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(string2);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}
	
	@Then("I update resume for instahyre")
	public void i_update_resume_for_instahyre() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement updateResumeButton = driver.findElement(By.xpath("(//label[@for='resume-input'])[2]"));
		wait.until(ExpectedConditions.visibilityOf(updateResumeButton));
		updateResumeButton.click();
		Thread.sleep(5000);
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys(
//				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/test/resources/utilities/Tarunkishore_Resume.pdf");
				"/Users/tarunkishore/git/Automation/src/test/resources/utilities/Tarunkishore_Resume.pdf");
		Thread.sleep(3000);	
	}
}
