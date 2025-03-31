package Stepsdefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.*;

public class MouseKeyActions {
	WebDriver driver = Common.driver;
	ExtentTest extentTest = Common.extentTest;
	ExtentReports extentReports = Common.extentReports;

	@Then("I right click on {string} and open in new Window")
	public void i_right_click_on_and_open_in_new_window(String string) throws IOException {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
		System.out.println("Looking for search button : " + string);
		String searchTerm = prop.getProperty(string);
//		driver.findElement(By.xpath(searchTerm));
//		actions = new Actions(driver);
//		WebElement rightClick = driver.findElement(By.xpath(string));
		Actions actions = new Actions(driver);
		actions.contextClick(driver.findElement(By.xpath(searchTerm))).perform();
		
	}
	
	@Then("I mouseHover on {string}")
	public void i_mouse_hover_on(String string) throws IOException {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
		System.out.println("Looking for search button : " + string);
		String searchTerm = prop.getProperty(string);
//		actions = new Actions(driver);
		Actions actions = new Actions(driver);
		WebElement mouseHover = driver.findElement(By.xpath(searchTerm));
		actions.moveToElement(mouseHover).perform();
		
	}
	
	@Then("I mouseHover on Naukari Job")
	public void i_mouse_hover_on_naukari_job() throws InterruptedException {
		Actions actions = new Actions(driver);
		WebElement mouseHover = driver.findElement(By.xpath("//div[text()='Jobs']"));
		actions.moveToElement(mouseHover).perform();
		Thread.sleep(1000);
	}
	
	@And("I select the {string}")
	public void i_select_the(String string) throws IOException {
		FileReader reader = new FileReader(
				"/Users/tarunkishore/eclipse-workspace/SeCuGhBDDTng/src/main/resources/Pageobjects/pageobject.properties");
		Properties prop = new Properties();
		prop.load(reader);
		System.out.println("Looking for search button : " + string);
		String searchTerm = prop.getProperty(string);
		driver.findElement(By.xpath(searchTerm)).click();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
