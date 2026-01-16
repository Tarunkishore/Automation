package Stepsdefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Stepsdefinitions.Common.PasswordManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Naukari {
	WebDriver driver = Common.driver;
	ExtentTest extentTest = Common.extentTest;
	ExtentReports extentReports = Common.extentReports;
	WebDriverWait wait = Common.wait;

	@When("I Search naukari hardcode URL and open successflly")
	public void i_search_naukari_hardcode_url_and_open_successflly() throws IOException {
		driver.get("https://www.naukri.com/mnjuser/homepage");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		System.out.println("Search naukari and open sussessfully");
	}

	@Then("I upload Resume on Naukari")
	public void i_upload_resume_on_naukari() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement resumeInput = wait.until(ExpectedConditions.presenceOfElementLocated(
		                By.xpath("//input[@type='file' and @id='attachCV']")));
		resumeInput.sendKeys("/Users/tarunkishore/git/Automation/src/test/resources/utilities/Tarunkishore_Resume.pdf");
		Thread.sleep(3000);	
		wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='updateOn typ-14Regular']"), 
				driver.findElement(By.xpath("//div[@class='updateOn typ-14Regular']")).getText()));
		System.out.println("Found : "+driver.findElement(By.xpath("//div[@class='updateOn typ-14Regular']")).getText());
	
	}

	@When("I enter {string} in {string}")
	public void i_enter_in(String string, String string2) throws IOException, InterruptedException {
		String searchTerm = Common.pageobjectVal(string2);
		driver.findElement(By.xpath(searchTerm)).sendKeys(string);
		Thread.sleep(2000);
	}

	@When("I provide {string} and open successflly")
	public void i_provide_and_open_successflly(String string) throws IOException {
		String searchTerm = Common.configVal(string);
		driver.get(searchTerm);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		System.out.println("Provide naukari URL and open sussessfully");
	}
//	@When("I enter the {string} in {string}")
//	public void i_enter_the_in(String string, String string2) throws IOException, InterruptedException  {
//		String searchTerm = Common.configVal(string);
//		String searchTerm2 =Common.pageobjectVal(string2);
//		driver.findElement(By.xpath(searchTerm2)).sendKeys(searchTerm);
//		Thread.sleep(1000);
//	}

	

	

}
