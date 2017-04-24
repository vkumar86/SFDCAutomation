package reporting;

import com.relevantcodes.extentreports.LogStatus;
import library.InitialSetUp;

public class TestLogger extends InitialSetUp {

	public TestLogger() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void log(String Status, String message) {
		switch (Status) {
		case "Pass": {
			test.log(LogStatus.PASS, message + "<br>");
			break;
		}
		case "Info": {
			test.log(LogStatus.INFO, message + "<br>");
			break;
		}
		case "Fail": {
			test.log(LogStatus.FAIL, message + "<br>");
			break;
		}
		}
	}
}