package utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.Table.Cell;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;

public class UtilityClass {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    //  MOVE launchBrowser() HERE (not inside another class)
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void launchBrowser(String browser, String url) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(url);
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    

    public String[][] readExcel(String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/data/Adactin.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Sheet with name '" + sheetName + "' not found in Excel file.");
        }

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowCount][colCount];

        DataFormatter formatter = new DataFormatter(); // handles all cell types

        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) continue;

            for (int j = 0; j < colCount; j++) {
                try {
                    XSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        data[i - 1][j] = formatter.formatCellValue(cell).trim();
                    } else {
                        data[i - 1][j] = "";
                        System.out.println("Warning: Null cell at row " + i + ", column " + j);
                    }
                } catch (Exception e) {
                    data[i - 1][j] = "";
                    System.out.println("Warning: Invalid cell at row " + i + ", column " + j);
                }
            }
        }

        workbook.close();
        System.out.println("Data read successfully from sheet: " + sheetName);
        return data;
    }

    public static void waitForElementToBeVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public void selectDropdownByVisibleText(WebElement element, String visibleText) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
    }
}
