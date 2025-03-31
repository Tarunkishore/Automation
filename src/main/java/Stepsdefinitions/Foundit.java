package Stepsdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.*;

public class Foundit {
	WebDriver driver = Common.driver;
	ExtentTest extentTest = Common.extentTest;
	ExtentReports extentReports = Common.extentReports;
	
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

}
