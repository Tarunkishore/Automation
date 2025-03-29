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
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/test/resources/utilities/Tarunkishore_Resume.pdf");
	}
	
	@When("I enter {string} in {string}")
	public void i_enter_in(String string, String string2) throws IOException, InterruptedException  {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
		String searchTerm = prop.getProperty(string2);
		driver.findElement(By.xpath(searchTerm)).sendKeys(string);
		Thread.sleep(1000);
	}
	
	@When("I enter the {string} in {string}")
	public void i_enter_the_in(String string, String string2) throws IOException, InterruptedException  {
		String searchTerm = Naukari.configVal(string);
		String searchTerm2 =Naukari.pageobjectVal(string2);
//		FileReader reader = new FileReader(
//				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/config/configuration.properties");
//		Properties prop = new Properties();
//		prop.load(reader);
		
//		FileReader reader2 = new FileReader(
//				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
//		Properties prop2 = new Properties();
//		prop2.load(reader2);
		
//		String searchTerm = prop.getProperty(string);
//		String searchTerm2 = prop2.getProperty(string2);
		driver.findElement(By.xpath(searchTerm2)).sendKeys(searchTerm);
		Thread.sleep(1000);
	}
	
	public static String pageobjectVal(String string) throws IOException {
		FileReader reader2 = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop2 = new Properties();
		prop2.load(reader2);
		String searchTerm = prop2.getProperty(string);
		return searchTerm;
	}
	
	public static String configVal(String string) throws IOException {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/config/configuration.properties");
		Properties prop = new Properties();
		prop.load(reader);
		String searchTerm = prop.getProperty(string);
		return searchTerm;
	}
	
	@When("I provide {string} and open successflly")
	public void i_provide_and_open_successflly(String string) throws IOException {
		String searchTerm =Naukari.pageobjectVal(string);
		driver.get(searchTerm);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		System.out.println("Search naukari and open sussessfully");
	}
	
//	@Then("I enter {string} and {string}")
//	public void i_enter_and(String string, String string2) {
//		driver.findElement(By.xpath("//input[@id='usernameField']")).sendKeys(string);
//		driver.findElement(By.xpath("//input[@id='passwordField']")).sendKeys(string2);
//	}
	
//	@Then ("I enter {string} in {string}")
//	public void i_enter_in(String string, String string2) {
//		driver.findElement(By.xpath("//input[@id='usernameField']")).sendKeys(string);
//		driver.findElement(By.xpath("//input[@id='passwordField']")).sendKeys(string2);
//	}
	
}
