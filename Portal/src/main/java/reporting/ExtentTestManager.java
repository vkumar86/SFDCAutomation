package reporting;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.*;

import library.InitialSetUp;

public class ExtentTestManager extends InitialSetUp {
	
    public ExtentTestManager() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    private static ExtentReports extent = ExtentManager.getInstance();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {	
        extent.endTest(extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}