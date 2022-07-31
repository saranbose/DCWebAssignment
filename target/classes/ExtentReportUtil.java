package resources;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {
	
	public static ExtentReports report;
	public static ExtentReports getExtentReportObject()
	{
		String sparkReportName = System.getProperty("user.dir")+"\\reports\\TestReport.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(sparkReportName);
		sparkReporter.config().setDocumentTitle("Framework Report");
		sparkReporter.config().setReportName("Functional Test Report");
		
		report = new ExtentReports();
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Tester", "Saravana");
		return report;
	}

}
