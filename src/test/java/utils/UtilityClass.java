package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class UtilityClass {

	public static WebDriver driver;
	public String sheetname;
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testname, testdescription, testCategory, testAuthor; 
	
	
	public void launchBrowser(String browser,String url) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(url);
		
	}
	
	public void closeBrowser() {
		
		driver.close();
	}
	
	public String[][] readExcel(String sheetName) throws IOException {
	    FileInputStream fis = new FileInputStream("src/test/resources/data/ContactListTestData.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    XSSFSheet sheet = workbook.getSheet(sheetName);  // uses "Signup"
	    
	    int rowCount = sheet.getLastRowNum();
	    int colCount = sheet.getRow(0).getLastCellNum();

	    String[][] data = new String[rowCount][colCount];

	    for (int i = 1; i <= rowCount; i++) {
	        for (int j = 0; j < colCount; j++) {
	            data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
	        }
	    }

	    workbook.close();
	    return data;
	}

	
	public static String screenShot(String name) throws IOException {
		
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String path="C:\\Users\\Dolly\\eclipse-workspace\\DemoProject\\ContactListApp\\src\\test\\resources\\snap\\"+name+timestamp+".png";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	}
	
	
	 public static void waitForElementToBeVisible(WebDriver driver, By locator) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
}
	

