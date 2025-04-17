package javaCodePractice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class P {
	static WebDriver driver = new ChromeDriver();
	static int activePage = 1;

	public static void main(String[] args) throws InterruptedException {

		P.futuredatePicker(driver,"2026","May","5");
		P.pastdatePicker(driver, "2021", "January", "26");
//		P.dynamicPaginationTable();
//		P.staticTable();
//		P.getElementOfSearchhead();
//		P.bootStrapSelectorDropdown();
//		P.frameHandle();
//		P.selectDropDown();
//		P.charSearch();
//		P.charCount();
		driver.quit();

	}
	
	public static void futuredatePicker(WebDriver driver, String year, String month, String date) throws InterruptedException {
		System.out.println(".................Future Date Picker Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/datepicker/");
		driver.switchTo().frame(0);
//		driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("05/05/1998");
//		System.out.println("Date picker by sendkey");
		Thread.sleep(1000);
		
		// without using sendKey directly to picker
//		String year="1998";
//		String month="May";
//		String date="5";
		
		driver.findElement(By.xpath("//input[@id='datepicker']")).click(); // open date picker calendar
		//select month and year
		while(true) {
			String currentMonth=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText(); // actual month from application
			String currentYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText(); // actual year from application
			if(currentMonth.equals(month) && currentYear.equals(year)) {
				break;
			}
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click(); //Next
//			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click(); //Previous
		}
		// select the date
		List<WebElement> alldates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td//a"));
		for(WebElement dt : alldates) {
			if(dt.getText().equals(date)) {
				System.out.println("date clicked");
				dt.click();
				break;
			}
		}
		System.out.println("selected : "+date+"/"+month+"/"+year);
		System.out.println(".................Future Date Picker End.....................");
	}
	
	public static void pastdatePicker(WebDriver driver, String year, String month, String date) throws InterruptedException {
		System.out.println(".................Past Date Picker Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/datepicker/");
		driver.switchTo().frame(0);
//		driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("05/05/1998");
//		System.out.println("Date picker by sendkey");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@id='datepicker']")).click(); // open date picker calendar
		//select month and year
		while(true) {
			String currentMonth=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText(); // actual month from application
			String currentYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText(); // actual year from application
			if(currentMonth.equals(month) && currentYear.equals(year)) {
				break;
			}
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click(); //Previous
		}
		// select the date
		List<WebElement> alldates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td//a"));
		for(WebElement dt : alldates) {
			if(dt.getText().equals(date)) {
				System.out.println("date clicked");
				dt.click();
				break;
			}
		}
		System.out.println("selected : "+date+"/"+month+"/"+year);
		System.out.println(".................Past Date Picker End.....................");
	}

	public static void dynamicPaginationTable() throws InterruptedException {
		System.out.println(".................Dynamic Pagination Table Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		// Scroll to view that element
		WebElement element = driver.findElement(By.xpath("//h2[text()='Pagination Web Table']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		int rows = driver.findElements(By.xpath("//table[@id='productTable']//tr")).size();
		int cols = driver.findElements(By.xpath("//table[@id='productTable']//th")).size();
		int page = driver.findElements(By.xpath("//ul[@id='pagination']//li")).size();
		System.out.println("Row : " + rows + "\n" + "cloumn : " + cols + "\n" + "Page Count : " + page);
		while (activePage <= page) {
			System.out.println();
			System.out.println("Active Number is : " + activePage);
			for (int r = 1; r < rows; r++) {
				for (int c = 1; c <= cols; c++) {
					if (c < cols) {
						String ele = driver.findElement(By.xpath("//table[@id='productTable']//tbody//tr[" + r + "]//td[" + c + "]")).getText();
						System.out.print(ele+"	");
					}
					if (c == 4) {
						c = c * 2 + r - 1;
						driver.findElement(By.xpath("(//input[@type='checkbox'])[" + c + "]")).click();
						Thread.sleep(150);
					}
				}
				System.out.println();
			}
			activePage++;
			if (activePage <= page) {
				driver.findElement(By.xpath("//ul[@id='pagination']//li[" + activePage + "]")).click();
			}
		}
		System.out.println(".................Dynamic Pagination Table End.....................");
	}

	public static void staticTable() throws InterruptedException {
		System.out.println(".................Static Table Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");

		WebElement element = driver.findElement(By.xpath("//h2[text()='Static Web Table']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
//		Thread.sleep(1000);
		System.out.println("scroll to view");

		int rows = driver.findElements(By.xpath("//table[@name='BookTable']//tr")).size();
		int columns = driver.findElements(By.xpath("//table[@name='BookTable']//th")).size();
		System.out.println(
				"Number of Row in Book Table : " + rows + "\n" + "Number of Column in Book Table : " + columns);

		// read all the data of table row and column
		for (int r = 2; r <= rows; r++) {
			for (int c = 1; c <= columns; c++) {
				String ele = driver.findElement(By.xpath("//table[@name='BookTable']//tr[" + r + "]//td[" + c + "]"))
						.getText();
				System.out.print(ele + "	|	");
			}
			System.out.println();
		}

		System.out.println(".................Static Table End.....................");
	}

	public static void getElementOfSearchhead() {
		System.out.println(".................Get Element of Search Head Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.google.com");

		driver.findElement(By.name("q")).sendKeys("selenium");
		System.out.println("Text selenium entered in search box");

		List<WebElement> options = driver.findElements(By.xpath("//ul[@role='listbox']//lo/div[@role='option']"));
		System.out.println(options.size());

		for (WebElement op : options) {
			System.out.println(op.getText());
		}

		System.out.println(".................Get Element of Search Head End.....................");
	}

	public static void bootStrapSelectorDropdown() {
		System.out.println(".................Bootstrap Selector Dropdown Start.....................");
		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//button[contains(@class,'multiselect')]")).click(); // open drop down options
		driver.findElement(By.xpath("//input[@value='Java']")).click(); // select single element
		System.out.println("Java selected");
		driver.findElement(By.xpath("//input[@value='Java']")).click(); // unselect single element
		System.out.println("Java Unselected");

		// capture all the options of drop and find the size
		List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class,'multiselect')]//label"));

		// print element by normal for loop
		System.out.println("..................Element print by Normal for loop Start..................");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(options.get(i).getText());
		}
		System.out.println("..................Element print by Normal for loop End..................");

		// print element by enhance for loop
		System.out.println("..................Element print by enhance for loop Start..................");
		for (WebElement op : options) {
			System.out.println(op.getText());
		}
		System.out.println("..................Element print by enhance for loop End..................");

		// Select check box which you want
		System.out.println("..................Select check box which you want Start..................");
		for (WebElement op : options) {
			String str = op.getText();
			if (str.equals("Java") || str.equals("Python") || str.equals("MySQL")) {
				op.click();
				System.out.println(str);
			}
		}
		System.out.println("..................Select check box which you want End..................");

		System.out.println(".................Bootstrap Selector Dropdown End.....................");
	}

	public static void selectDropDown() throws InterruptedException {
		System.out.println(".................Select From Dropdown Start.....................");
		driver.get("https://testautomationpractice.blogspot.com/");

		WebElement element = driver.findElement(By.xpath("//label[text()='Country:']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);
		System.out.println("scroll to view");

		WebElement drpEle = driver.findElement(By.xpath("//select[@id='country']"));
		Select drp = new Select(drpEle);

//		drp.selectByContainsVisibleText("France");
//		drp.selectByValue("japan");
		drp.selectByIndex(9);
		System.out.println("select from dropdown");

		List<WebElement> options = drp.getOptions();
		System.out.println("Number of options in dropdown" + options.size());
		for (WebElement op : options) {
			System.out.println(op.getText());
		}
		System.out.println(".................Select From Dropdown End.....................");
	}

	// Character Count in string
	public static void charCount() {
		System.out.println(".................Char Count Start.....................");
		String name = "Automation Testing using Selenium java";
//		String name = "Automation";
		name = name.toLowerCase();
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		char strArray[] = name.toCharArray();
		for (char c : strArray) {
			if (charMap.containsKey(c)) {
				charMap.put(c, charMap.get(c) + 1);
			} else {
				charMap.put(c, 1);
			}
		}
		System.out.println(name + " : " + charMap);
		System.out.println(".................Char Count End.....................");
	}

	// Find the character "ind" present in the string
	public static void charSearch() {
		System.out.println(".................Char Search Start.....................");
		ArrayList<String> sc = new ArrayList<>();
		sc.add("india");
		sc.add("Indonesia");
		sc.add("British India");
		sc.add("Bihar");
		sc.add("Delhi");

		String str = "ind";
		for (String s : sc) {
			if (s.toLowerCase().contains(str))
				System.out.println(s);
		}
		System.out.println(".................Char Search End.....................");
	}

//	input  : Automation Testing
//	output : Automation gnitset
//	public void reverseString() {
//		ArrayList list = new ArrayList() {
//			
//		}
//	}

	public static void frameHandle() {
		System.out.println(".................Frame Handle Start.....................");
		System.out.println("Initializing Driver...");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://ui.vision/demo/webtest/frames/");

		WebElement frame1 = driver.findElement(By.xpath("//frame[@src='frame_1.html']"));
		driver.switchTo().frame(frame1);
		System.out.println("Switched to frame1...");
		driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("xyz");
		System.out.println("Value passed in frame 1 ...");
		driver.switchTo().defaultContent();

		WebElement frame2 = driver.findElement(By.xpath("//frame[@src='frame_2.html']"));
		driver.switchTo().frame(frame2);
		System.out.println("Switched to frame2...");
		driver.findElement(By.xpath("//input[@name='mytext2']")).sendKeys("xyz");
		System.out.println("Value passed in frame 2 ...");
		driver.switchTo().defaultContent();

		driver.switchTo().frame(frame1);
		System.out.println("Switched to frame1...");
		driver.findElement(By.xpath("//input[@name='mytext1']")).clear();
		System.out.println("Value deleted in frame 1 ...");
		driver.switchTo().defaultContent();

		WebElement frame3 = driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
		driver.switchTo().frame(frame3);
		System.out.println("Switched to frame3...");
		driver.findElement(By.xpath("//input[@name='mytext3']")).sendKeys("xyz");
		System.out.println("Value passed in frame 3 ...");
//		driver.switchTo().defaultContent();

		driver.switchTo().frame(0);
		driver.findElement(By.xpath("(//div[@class='AB7Lab Id5V1'])[2]")).click();
		driver.findElement(By.xpath("//span[text()='General Web Automation']")).click(); // select only particular check
																							// box

		// select multiple checkbox
		List<WebElement> chbox = driver
				.findElements(By.xpath("//div[@class='uVccjd aiSeRd FXLARc wGQFbe BJHAP oLlshd']"));
		for (WebElement checkbox : chbox) {
			checkbox.click();
		}
		System.out.println(".................Frame Handle End.....................");
	}

	public static void checkBox() {
		System.out.println(".................check Box Start.....................");
//		 select specific check box
		driver.findElement(By.xpath(null)).click();

//		select all check box
		List<WebElement> checkboxes = driver.findElements(By.xpath(null));
		for (int i = 0; i < checkboxes.size(); i++) {
			checkboxes.get(i).click();
		}
		for (WebElement cb : checkboxes) {
			cb.click();
		}

//		 select last 3 check boxes
		for (int i = 4; i < checkboxes.size(); i++) {
			checkboxes.get(i).click();
		}
//		 select first 3 check box
		for (int i = 0; i < 3; i++) {
			checkboxes.get(i).click();
		}
		System.out.println(".................check Box End.....................");
	}

}
