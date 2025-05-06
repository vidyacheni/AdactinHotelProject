package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Adactin Hotel Test Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "YourName");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Edge or Firefox");

        return extent;
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }
}
