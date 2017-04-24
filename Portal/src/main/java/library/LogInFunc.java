package library;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import objectRepo.*;

public class LogInFunc extends GeneralFunc implements LogInObj {
	
	GeneralFunc generalFun = new GeneralFunc();

	public LogInFunc() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public void LoginTest(WebDriver driver, String username, String password) {
		try {
			
			if (driver.toString().contains("null")) {
				try {
					
					InitialSetUp brwObj = new InitialSetUp();
					brwObj.instantiateWebDriver();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			generalFun.buildDriverElement(logUserName).sendKeys(username);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    generalFun.buildDriverElement(logPassword).sendKeys(password);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebDriverWait driverWait;
			driverWait = new WebDriverWait(driver,explicitTimeout);
			driverWait.until(ExpectedConditions.elementToBeClickable(generalFun.buildLocator(logLogin)));
			generalFun.isElementExists(generalFun.buildDriverElement(logLogin), "logLogin button exists");	
			generalFun.buildDriverElement(logLogin).submit();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void LogoutTest(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
		
	

	}
	
}