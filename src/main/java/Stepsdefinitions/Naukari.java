package Stepsdefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Stepsdefinitions.Common.PasswordManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Naukari {
	WebDriver driver = Common.driver;
	ExtentTest extentTest = Common.extentTest;
	ExtentReports extentReports = Common.extentReports;

	@When("I Search naukari hardcode URL and open successflly")
	public void i_search_naukari_hardcode_url_and_open_successflly() throws IOException {
		driver.get("https://www.naukri.com/mnjuser/homepage");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		System.out.println("Search naukari and open sussessfully");
	}

	@Then("I upload Resume on Naukari")
	public void i_upload_resume_on_naukari() throws InterruptedException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/test/resources/utilities/Tarunkishore_Resume.pdf";
		String resumePath = projectPath + path;
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys(resumePath);
		Thread.sleep(5000);
	}
	
	@Then("I upload Resume on Instahyre")
	public void i_upload_resume_on_instahyre() throws InterruptedException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/test/resources/utilities/Tarunkishore_Resume.pdf";
		String resumePath = projectPath + path;
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys(resumePath);
		Thread.sleep(5000);
	}
	
	@Then("I upload Resume on Foundit Monster")
	public void i_upload_resume_on_foundit_monster() throws InterruptedException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/test/resources/utilities/Tarunkishore_Resume.pdf";
		String resumePath = projectPath + path;
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys(resumePath);
		Thread.sleep(5000);
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
