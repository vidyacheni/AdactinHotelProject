package base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.UtilityClass;

public class ProjectSpecificationMethods extends UtilityClass{

	@BeforeSuite
	public void reportInitialization() {
		
		//To create report in the given location
		ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Users\\Digital Suppliers\\second-workspace\\ContactListApp\\src\\test\\resources\\report\\ContactListAppReport.html");
		reporter.config().setReportName("Contact List App Report");
		
		//To capture the test data
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeClass
	public void testDetails() {
		
		test=extent.createTest(testname,testdescription);
		test.assignCategory(testCategory);
		test.assignAuthor(testAuthor);
		
	}
	
	@Parameters({"browser","url"})
	@BeforeMethod
	public void launchingAndLoadingURL(String browser, String url) {
		
		launchBrowser(browser,url);
		
	}
	
	@AfterMethod
	public void browserClose() {
		
		closeBrowser();
	}
	
	@DataProvider
	public String[][] readData() throws IOException {
		
		String[][] data = readExcel(sheetname);
		return data;
	}
	
	@AfterSuite
	public void reportClose() {
		
		extent.flush();  // Mandatory if missed report will not be created.
	}

}
