package javaCodePractice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Stepsdefinitions.Common;

public class P {
	static WebDriver driver = new ChromeDriver();
	static char rev[];
	static int activePage = 1;
	public static WebDriverWait wait;

	public static void main(String[] args) throws InterruptedException, IOException {
		
//		P.writeDynamicIntoExcel();
//		P.writeIntoExcel();
//		P.readFromExcel();
//		P.dragAndDrop();
//		P.rightClickMouseAction();
//		P.mouseAction();
//		P.reverseString();
//		P.futuredatePicker(driver,"2026","May","5");
//		P.pastdatePicker(driver, "2021", "January", "26");
//		P.dynamicPaginationTable();
//		P.staticTable();
//		P.getElementOfSearchhead();
//		P.bootStrapSelectorDropdown();
//		P.frameHandle();
//		P.selectDropDown();
		P.handleWindowNavigation();
		
		driver.quit();
	}
	
	public static void handleWindowNavigation() {
	    driver.get("https://www.youtube.com");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search_query"))).sendKeys("TMKOC EP 101");
	    driver.findElement(By.xpath("(//button[@title='Search'])[1]")).click();
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='video-title']")));
	    for (int i = 0; i < 5; i++) {

	        // Re-locate elements every loop
	        List<WebElement> videos = driver.findElements(By.xpath("//a[@id='video-title']"));
	        WebElement video = videos.get(i);
			String expectedTitle = video.getDomAttribute("title");
	        video.click();
	        wait.until(ExpectedConditions.titleContains(expectedTitle));
	        System.out.println("Title : " + driver.getTitle());
	        System.out.println("URL   : " + driver.getCurrentUrl());
	        driver.navigate().back();
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='video-title']")));
	    }
	}

	
	public static void writeDynamicIntoExcel() throws IOException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/test/resources/utilities/Book3.xlsx";
		String excelPath = projectPath + path;
		FileOutputStream file = new FileOutputStream(excelPath);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Data");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of rows : ");
		int numOfRows=sc.nextInt();
		
		System.out.println("Enter number of cells : ");
		int numOfCells=sc.nextInt();
		
		for(int r=0; r<numOfRows; r++) {
			XSSFRow currentRow=sheet.createRow(r);
			
			for(int c=0; c<numOfCells; c++) {
				XSSFCell cell=currentRow.createCell(c);
				cell.setCellValue(sc.next());
				
			}
		}
		
		workbook.write(file);
		workbook.close();
		file.close();
		sc.close();
		System.out.println("File Created...");
	}
	
	
	public static void writeIntoExcel() throws IOException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/test/resources/utilities/Book2.xlsx";
		String excelPath = projectPath + path;
		FileOutputStream file = new FileOutputStream(excelPath);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Data");
		
		XSSFRow row1=sheet.createRow(0);
		row1.createCell(0).setCellValue("Java1");
		row1.createCell(1).setCellValue("12345");
		row1.createCell(2).setCellValue("Auto1");
		
		XSSFRow row2=sheet.createRow(1);
		row2.createCell(0).setCellValue("Java2");
		row2.createCell(1).setCellValue("67890");
		row2.createCell(2).setCellValue("Auto2");
		
		XSSFRow row3=sheet.createRow(2);
		row3.createCell(0).setCellValue("Java3");
		row3.createCell(1).setCellValue("11121");
		row3.createCell(2).setCellValue("Auto3");
		
		XSSFRow row4=sheet.createRow(3);
		row4.createCell(0).setCellValue("Java4");
		row4.createCell(1).setCellValue("13141");
		row4.createCell(2).setCellValue("Auto4");
		
		workbook.write(file);
		workbook.close();
		file.close();
		System.out.println("File Created...");
	}
	
	public static void readFromExcel() throws IOException {
		String projectPath = System.getProperty("user.dir");
		String path = "/src/test/resources/utilities/Book1.xlsx";
//		String path = "/src/test/resources/utilities/Daily_Kharcha.xlsx";
		String excelPath = projectPath + path;
		FileInputStream file=new FileInputStream(excelPath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheet("Yearly");
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int totalRows = sheet.getLastRowNum();
		int totalCells = sheet.getRow(1).getLastCellNum();
		System.out.println("number of rows : "+totalRows+"\n"+"number of Cells : "+totalCells);
		
		for(int r=0; r<=totalRows; r++) {
			XSSFRow currentRow = sheet.getRow(r);
			for(int c=0; c<totalCells; c++) {
				XSSFCell cell = currentRow.getCell(c);
				System.out.print(cell.toString()+"  |  ");
			}
			System.out.print("\n");
		}
		workbook.close();
		file.close();
	}
	
	public static void array1() {
		int arr[] = {1,2,3,4,5};
		for(int i=0; i<=arr.length-1; i++) {
			System.out.print(arr[i]);
			if(i<arr.length-1) {
				System.out.print(",");
			}
		}
	}
	public static void array3() {
		int arr[] = {1,2,3,4,5};
//		for(int num:arr[]) {
//			System.out.print(arr[i]);
//			if(i<arr.length-1) {
				System.out.print(",");
//			}
//		}
				System.out.println(arr);
	}
	
	public static void array2() {
		String strArr[] = {"india","indonesia","British India","Japan","USA","Russia"};
		String input = "ind";
		
	
		System.out.println(strArr);
		System.out.println(input);
	
	}
	
	public static void pattern2(int row, int col) {
		for (int i = row; i >= row; i--) {
			for (int j = col; j >= col; j--) {
				
				System.out.print("*");
			}
			System.out.println();
		}
	}

	public static void pattern1(int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col - i; j++) {
				
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public static void dragAndDrop() throws InterruptedException {
		System.out.println(".................Drag And Drop Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
		
		Actions act = new Actions(driver);
		WebElement rome = driver.findElement(By.xpath("//div[@id='box6']"));
		WebElement italy = driver.findElement(By.xpath("//div[@id='box106']"));
		act.dragAndDrop(rome, italy).perform();
		Thread.sleep(1000);
		
		WebElement romewashington = driver.findElement(By.xpath("//div[@id='box3']"));
		WebElement us = driver.findElement(By.xpath("//div[@id='box103']"));
		act.dragAndDrop(romewashington, us).perform();
		Thread.sleep(1000);
		
		System.out.println(".................Drag And Drop Start.....................");
	}
	
	public static void rightClickMouseAction() throws InterruptedException {
		System.out.println(".................Right Click Mouse Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickMe = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions act = new Actions(driver);
		act.contextClick(rightClickMe).perform();	// right click on element
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Copy']")).click();	// after click alert popup 
		Thread.sleep(1000);
		driver.switchTo().alert().accept();	// accept alert
		Thread.sleep(1000);		
		System.out.println(".................Right Click Mouse End.....................");
	}
	
	public static void mouseAction() {
		System.out.println(".................Mouse Hover Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.switchTo().defaultContent();
		
		Actions act= new Actions(driver);
		WebElement fashion = driver.findElement(By.xpath("//span[text()='Fashion']"));
		WebElement womenEthnic = driver.findElement(By.xpath("//a[text()='Women Ethnic']"));
		WebElement womenSaree = driver.findElement(By.xpath("//a[text()='Women Sarees']"));
		act.moveToElement(fashion).moveToElement(womenEthnic).moveToElement(womenSaree).perform();
		
		System.out.println(".................Mouse Hover End.....................");
		
	}
	
//	input  : Automation Testing
//	output : Automation gnitset
	public static void reverseString() {
		String input = "Automation Testing";
		String word[] = input.split(" ");
		System.out.println("input at Word[0] : "+word[0]);
		System.out.println("input at Word[1] : "+word[1]);
		
		char chrarray[] = word[1].toCharArray();
		char rev[] = new char[chrarray.length];
		
		System.out.println("\nCharacter Array without reverse\n");
		
		for(int i=0; i<chrarray.length; i++) {
			System.out.println("Character at chrarray["+i+"] : "+chrarray[i]);
		}
		System.out.println("\nCharacter Array after reversing\n");
		System.out.print(word[0]+" ");
		
		for(int i=chrarray.length; i>0; i--) {
			rev[chrarray.length-i]= chrarray[i-1];
	
			System.out.print(rev[chrarray.length-i]);
		}	
	}
	
	public static void futuredatePicker(WebDriver driver, String year, String month, String date) throws InterruptedException {
		System.out.println(".................Future Date Picker Start.....................");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/datepicker/");
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@id='datepicker']")).click(); // open date picker calendar
		//select month and year
		while(true) {
			String currentMonth=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText(); // actual month from application
			String currentYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText(); // actual year from application
			if(currentMonth.equals(month) && currentYear.equals(year)) {
				break;
			}
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click(); //Next
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
