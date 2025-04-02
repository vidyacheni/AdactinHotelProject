package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.UtilityClass;

public class ProjectSpecificationMethods extends UtilityClass{

	@BeforeMethod
	public void launchingAndLoadingURL() {
		
		launchBrowser("chrome","https://thinking-tester-contact-list.herokuapp.com/");
		
	}
	
	@AfterMethod
	public void browserClose() {
		
		closeBrowser();
	}

}
