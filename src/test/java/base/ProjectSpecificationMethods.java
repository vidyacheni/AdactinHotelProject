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
		ExtentSparkReporter reporter = new ExtentSparkReporter("src/test/resources/report/ContactListAppReport.html");

		reporter.config().setReportName("Contact List App Report");
		
		//To capture the test data
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeClass
	public void testDetails() {
	    // Default values
	    if (testname == null || testname.trim().isEmpty()) {
	        testname = this.getClass().getSimpleName(); // Use class name as default test name
	    }
	    if (testdescription == null || testdescription.trim().isEmpty()) {
	        testdescription = "No description provided"; // Default description
	    }
	    if (testCategory == null || testCategory.trim().isEmpty()) {
	        testCategory = "Functional"; // Default category
	    }
	    if (testAuthor == null || testAuthor.trim().isEmpty()) {
	        testAuthor = "Test Author"; // Default author
	    }

	    // Create the test in ExtentReports with the provided name and description
	    test = extent.createTest(testname, testdescription);
	    
	    // Assign categories and authors if available
	    if (testCategory != null) {
	        test.assignCategory(testCategory);
	    }
	    if (testAuthor != null) {
	        test.assignAuthor(testAuthor);
	    }
	}

    @Parameters({"browser", "url"})
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
	    UtilityClass util = new UtilityClass();
	    return util.readExcel(sheetname);  // uses the sheet name "Signup"
	}
	
	@AfterSuite
	public void reportClose() {
		
		extent.flush();  // Mandatory if missed report will not be created.
	}

}