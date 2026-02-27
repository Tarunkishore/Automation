package TestRunner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Stepsdefinitions.Common;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		dryRun = false,
		monochrome = true,
//		features = {"${cucumber.features}"},
		features = { "src/main/resources/Features" }, // folder name
		glue = { "Stepsdefinitions" }, // package name for step def

//		tags = "${cucumber.filter.tags}",
//		tags = System.getProperty("cucumber.filter.tags", "@TMKOC"),
//		tags = "@naukari",
//		tags = "@TMKOC",
//		tags = System.getProperty("cucumber.filter.tags", "@TMKOC"),
		plugin = { "pretty",
				"Stepsdefinitions.CucumberStepListener",
//				"html:target/cucumber-reports/cucumber.html",  // HTML Report
//				"json:target/cucumber-reports/cucumber.json",  // JSON Report
//				"junit:target/cucumber-reports/cucumber.xml",
				
//				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumnerAdapter:",
		}
//		plugin = { "pretty","html:target/cucumber.html" }// tags used in feature file
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
	 @BeforeTest
	 @Parameters({"browserName", "headless", "incognito"})
	 public void setUp(String browserName, String headless, String incognito) {
	 	Common.setup(browserName, headless, incognito);
	 }
}
