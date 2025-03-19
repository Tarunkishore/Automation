package Stepsdefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Naukari {
	WebDriver driver = Common.driver;
	ExtentTest extentTest = Common.extentTest;
	ExtentReports extentReports = Common.extentReports;

	@When("I Search naukari and open successflly")
	public void i_search_naukari_and_open_successflly() throws IOException {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
		driver.get(prop.getProperty("URL_naukari"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		System.out.println("Search naukari and open sussessfully");
	}

	@Then("I upload Resume on Naukari")
	public void i_upload_resume_on_naukari() throws InterruptedException {
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/test/resources/utilities/Tarunkishore_Analyst.docx");
	}
	
	
}
