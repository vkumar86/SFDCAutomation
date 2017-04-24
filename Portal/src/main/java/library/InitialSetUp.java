package library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.*;

import reporting.ExtentManager;
import reporting.ExtentTestManager;
import reporting.TestLogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class InitialSetUp {

	public static WebDriver driver;
	public static String dataFilePath;
	public static String documentPath;
	public static int explicitTimeout;
	public static int implicitTimeout;
	

	public String browser;
	public String url;
	public String cmd;
	public String chromeDriver;

	
	public WebElement webElement;
	public Properties config;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static int testCaseCount = 0;

	public InitialSetUp() throws Exception {
		config = new Properties();
		String propFilename = "config.properties";
		InputStream inputstream = getClass().getClassLoader().getResourceAsStream(propFilename);
		config.load(inputstream);

		browser = config.getProperty("BROWSER");
		url = config.getProperty("URL");
//		url = config.getProperty(System.getProperty("environment"));
		
		
		chromeDriver = config.getProperty("CHROME_DRIVER_LOC");
		dataFilePath = config.getProperty("DATA_LOCATION");
		documentPath = config.getProperty("DOC_LOCATION");
		explicitTimeout = Integer.parseInt(config.getProperty("EXPLICIT_TIMEOUT"));
		implicitTimeout = Integer.parseInt(config.getProperty("IMPLICIT_TIMEOUT"));
		
		
	}
	
	// Method to initiate browser and set browser capabilities
	@BeforeSuite(alwaysRun = true)
	public void instantiateWebDriver() throws Exception {
		try {			  
			if (browser.equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", chromeDriver);
				driver = new ChromeDriver();
				
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.chrome.driver", chromeDriver);
				driver = new ChromeDriver();
				
			}
			else  {
				System.setProperty("webdriver.chrome.driver", chromeDriver);
				driver = new ChromeDriver();
				
			}
			driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method to refresh browser after every class
	@AfterClass(alwaysRun = true)
	public void refreshBrowser() {
		try {
			if (!driver.toString().contains("null")) { 
				driver.navigate().to(url);
				Thread.sleep(5000);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Below methods are used for reporting purpose and supporting methods are present in reporting package */
	
	@BeforeSuite(alwaysRun = true)
	public void extentSetup(ITestContext context) {
		ExtentManager.setOutputDirectory(context);
		extent = ExtentManager.getInstance();
	}

	@BeforeMethod (alwaysRun = true)
	public void startExtent(Method method) {
		Test test = method.getAnnotation(Test.class);
		if (test == null) {
			return;
		}
		if (test.description() == null) {
			ExtentTestManager.startTest(method.getName());
		} else {
			ExtentTestManager.startTest(method.getName(), test.description());	
		}
	}

	protected String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	@AfterMethod (alwaysRun = true)
	public void afterEachTestMethod(ITestResult result) {
		ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
		ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));
		testCaseCount = testCaseCount + 1;
		
		for (String group : result.getMethod().getGroups()) {
			ExtentTestManager.getTest().assignCategory(group);
		}

		if (result.getStatus() == 1) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
			System.out.println(Integer.toString(testCaseCount) + " - (Passed) - " + result.getName() + " : " + result.getMethod().getDescription());
			
		} else if (result.getStatus() == 2) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
			System.out.println(Integer.toString(testCaseCount) + " - (Failed) - " + result.getName() + " : " + result.getMethod().getDescription());
			
		} else if (result.getStatus() == 3) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
			System.out.println(Integer.toString(testCaseCount) + " - (Skipped) - " + result.getName() + " : " + result.getMethod().getDescription());
		}
		
		ExtentTestManager.endTest();
		extent.flush();
	}

	@AfterSuite(alwaysRun = true)
	public void generateReport() throws InterruptedException, MessagingException {
		extent.close();
		//driver.manage().deleteAllCookies();
		this.sendEmail();
	//driver.quit();
		
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	public void sendEmail() throws MessagingException {
		
		String username = config.getProperty("E_USERNAME");
		String password = config.getProperty("E_PASSWORD");
		String to = config.getProperty("E_TO");
		String cc = config.getProperty("E_CC");
		String from = username;
		String eSubject = config.getProperty("E_SUBJECT");
		String ebody = config.getProperty("E_BODY");

		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.required", "true");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));

			// Set Subject: header field
			message.setSubject(eSubject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
            Multipart messageMultiPart = new MimeMultipart();
			// Now set the actual message
			message.setContent(ebody, "text/html; charset=utf-8");

			// Set the email attachment file	            
			System.out.println("Attachment path : " + ExtentManager.reportFilename);
			FileDataSource fileDataSource = new FileDataSource(ExtentManager.reportFilename) {
				@Override
				public String getContentType() {
					return "application/octet-stream";
				}
			};
			
			messageBodyPart.setDataHandler(new DataHandler(fileDataSource));
			messageBodyPart.setFileName(ExtentManager.reportFilename);
			messageMultiPart.addBodyPart(messageBodyPart);
			message.setContent(messageMultiPart);
			
			// Send message
			Transport.send(message);

		} catch (MessagingException e) {
			throw e;
		} catch (Exception ex) {
			throw ex;
		}
	}
}