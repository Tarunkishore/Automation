package Stepsdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Naukari {
WebDriver driver = Common.driver;
	
	@When("Search naukari and open sussessfully")
	public void search_naukari_and_open_sussessfully() {
		
		driver.get("https://www.naukri.com/mnjuser/homepage");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		System.out.println("Search naukari and open sussessfully");
	}
	
	@Then("I upload Resume on Naukari")
	public void i_upload_resume_on_naukari() throws InterruptedException {
//		String projectPath = System.getProperty("user.dir"); 
//		System.out.println("ProjectFilePath : "+projectPath);
//		WebElement updateResumeButton = driver.findElement(By.xpath("//input[@type='button' and @value='Update resume']"));
//		updateResumeButton.click();
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys("/Users/tarunkishore/git/repository/BDDTDD/src/test/resources/Utilities/Tarunkishore_SQA.pdf");
	}
}
