package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG 

{
	public static  ExtentReports getreportobject() {
	String path=System.getProperty("user.dir")+"\\Reports\\index.html";
	System.out.println(path);
	ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	reporter.config().setReportName("Learn Reports");
	reporter.config().setDocumentTitle("Test Results");
	ExtentReports reports=new ExtentReports();
	reports.attachReporter(reporter);
	reports.setSystemInfo("Tester", "Thamil");
	return reports;

	}	}
