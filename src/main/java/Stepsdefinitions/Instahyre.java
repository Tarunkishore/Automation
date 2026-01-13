package Stepsdefinitions;

import java.io.IOException;
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
				count += 1;
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
	
	@Then("I update resume for instahyre")
	public void i_update_resume_for_instahyre() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement resumeInput = wait.until(ExpectedConditions.presenceOfElementLocated(
		                By.xpath("//input[@type='file' and @id='resume-input']")));
		resumeInput.sendKeys("/Users/tarunkishore/git/Automation/src/test/resources/utilities/Tarunkishore_Resume.pdf");
		Thread.sleep(3000);	
		wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='candidate-resume-uploaded-time ng-binding']"), 
				driver.findElement(By.xpath("//span[@class='candidate-resume-uploaded-time ng-binding']")).getText()
				));
		System.out.println("Found : "+driver.findElement(By.xpath("//span[@class='candidate-resume-uploaded-time ng-binding']")).getText());
	}
}
