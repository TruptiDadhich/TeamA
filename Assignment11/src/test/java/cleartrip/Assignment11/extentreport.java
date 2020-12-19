package cleartrip.Assignment11;

import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentreport {
	static ExtentReports report;
	@BeforeTest
	public static ExtentReports extentrepo() {
		String path = System.getProperty("user.dir") + "\\extendreports\\index.html";
		ExtentSparkReporter repo = new ExtentSparkReporter(path);
		repo.config().setReportName("ClearTrip");

		report = new ExtentReports();
		report.attachReporter(repo);
		return report;
	}
}
