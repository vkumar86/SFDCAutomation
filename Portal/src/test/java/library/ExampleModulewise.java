package library;
/*package com.pramati.Corridor.library;

import com.thoughtworks.selenium.SeleniumException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.gargoylesoftware.htmlunit.javascript.host.Element;
//import com.pwd.PWDUtil.data.*;
import com.pw.PWUtil.data.LoginData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LeaveAdmin extends InitialSetUp implements com.pw.PWUtil.data.LeaveAdmin {
    public String url;
    public String username;
    public String password;
    
    com.pw.PWUtil.library.Login login = new com.pw.PWUtil.library.Login();
    
	Properties config = new Properties();
	String propFilename = "login.properties";
	InputStream inputstream = getClass().getClassLoader().getResourceAsStream(propFilename);
	Properties configLeaveAdmin = new Properties();
	String propLeaveAdmin = "LeaveAdmin.properties";
	InputStream leaveInputStream = getClass().getClassLoader().getResourceAsStream(propLeaveAdmin);
    
	public LeaveAdmin(String url1) throws Exception {
//		String url1
         super();
         url=url1;
     	config.load(inputstream);
     	configLeaveAdmin.load(leaveInputStream);
//     	System.out.println("HEllo ->"+url1);
     }

 *//****************   ADD LEAVE TYPE   *****************//*
//     @Test(groups="FunctionalTest")
     public void addLeaveType() {
        	
// To get Username and password form property file   	 
    	username=config.getProperty("USERNAME");
    	password=config.getProperty("PASSWORD");
    	System.out.println("username -> "+username+"password->" + password);
// Login to the application with required role    	 
         try{
        	 
             login.LoginTest(driver,username,password);
             
// Navigate to "Leave Admin page" 
             WebDriverWait driverWait = new WebDriverWait(driver, explicitTimeout);
             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssPWAdminMenu)));
             
             Actions act = new Actions(driver);
             WebElement adminMenu = driver.findElement(By.cssSelector(cssPWAdminMenu));
             act.moveToElement(adminMenu).perform();
             driver.findElement(By.xpath(xpathSeeAllSubMenu)).click();
             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveAdmin)));
             driver.findElement(By.cssSelector(cssLeaveAdmin)).click();
             
//             Click on leave type link
             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveType)));
             driver.findElement(By.cssSelector(cssLeaveType)).click();
             
//             Click on add button
             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssAddLeaveType)));
             driver.findElement(By.cssSelector(cssAddLeaveType)).click();
             
//             Enter the name in name text field
             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeEdit)));
             driver.findElement(By.cssSelector(cssLeaveTypeEdit)).sendKeys(configLeaveAdmin.getProperty("LEAVE_TYPE"));
             
//             Enter the code in the code text field
             driver.findElement(By.cssSelector(cssLeaveCodeEdit)).sendKeys(configLeaveAdmin.getProperty("CODE"));
//             Enter the description
             driver.findElement(By.cssSelector(cssLeaveDescEdit)).sendKeys(configLeaveAdmin.getProperty("DESCRIPTION"));
//             Check/unchek the active box
             driver.findElement(By.cssSelector(cssLeaveTypeActive)).click();
             
//             Click on save and continue button 
             driver.findElement(By.xpath(xpathSaveAndContLT)).click();
             
///////////////////Click on save and close button//////////////////////////////
//           Enter the name in name text field
           driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeEdit)));
           driver.findElement(By.cssSelector(cssLeaveTypeEdit)).sendKeys(configLeaveAdmin.getProperty("LEAVE_TYPE"));
           
//           Enter the code in the code text field
           driver.findElement(By.cssSelector(cssLeaveCodeEdit)).sendKeys(configLeaveAdmin.getProperty("CODE"));

//           Enter the description
           driver.findElement(By.cssSelector(cssLeaveDescEdit)).sendKeys(configLeaveAdmin.getProperty("DESCRIPTION"));

//           Eheck/unchek the active box
           driver.findElement(By.cssSelector(cssLeaveTypeActive)).click();
           
//           Click on save and continue button 
           driver.findElement(By.xpath(xpathSaveAndCloseLT)).click();
           if(driver.findElement(By.cssSelector(cssLeaveTypeEdit)).isDisplayed())
           {
        	   org.testng.Assert.assertTrue(true, "Cannot input duplicate element");
           }
           else
           {
        	   org.testng.Assert.assertFalse(false, "Added duplicate input");
           }
//             Click on cancel button
           driver.findElement(By.xpath(xpathCancelLT)).click();

//////////////////////// Click on close button /////////////////////////
//         Click on add button
         driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssAddLeaveType)));
         driver.findElement(By.cssSelector(cssAddLeaveType)).click();
         
//         driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(xpathCancelLT)));
         driver.findElement(By.xpath(xpathCancelLT)).click();
         int flag = 0;
         do
         {
        	 try
        	 {
        		 if(driver.findElement(By.xpath("//td[contains(text(),'"+configLeaveAdmin.getProperty("LEAVE_TYPE")+"')]")).isDisplayed()){
        			 org.testng.Assert.assertTrue(true, "The value is added to table successfully");
        			 flag=1;
        			 break;
        		 }
        	 }
        	 catch(Exception e){
        		 driver.findElement(By.xpath(xpathNextButtonLeaveType)).click();
        	 }
         }while(driver.findElement(By.xpath(xpathNextButtonLeaveType)).isEnabled());
         if(flag != 1){
        	 org.testng.Assert.assertTrue(false,"The value is not added");
         }
         }catch (SeleniumException e){
        	System.out.println("Exception");
             e.printStackTrace();

         } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         
     }
     
*//****************  TEST TO EDIT LEAVE TYPE  *******************//*
//     @Test(groups="FunctionalTest",dependsOnMethods={"addLeaveType"})
     public void editLeaveType(){
    	 WebDriverWait driverWait = new WebDriverWait(driver, explicitTimeout);
//    	 Select the required records
    	 driver.findElement(By.xpath("//td[contains(text(),'"+configLeaveAdmin.getProperty("LEAVE_TYPE")+"')]/..//input[@id='Check']")).click();
    	 
//    	 Click on edit button.
    	 driver.findElement(By.cssSelector(cssEditLeaveType)).click();
    	 
//    	 Edit the name text field.
    	 driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeEdit)));
    	 driver.findElement(By.cssSelector(cssLeaveTypeEdit)).sendKeys(Keys.CONTROL + "a");
    	 driver.findElement(By.cssSelector(cssLeaveTypeEdit)).sendKeys(Keys.DELETE);
    	 driver.findElement(By.cssSelector(cssLeaveTypeEdit)).sendKeys(configLeaveAdmin.getProperty("EDIT_LEAVE_TYPE"));
         
//       Enter the code in the code text field
    	 driver.findElement(By.cssSelector(cssLeaveCodeEdit)).sendKeys(Keys.CONTROL + "a");
    	 driver.findElement(By.cssSelector(cssLeaveCodeEdit)).sendKeys(Keys.DELETE);
    	 driver.findElement(By.cssSelector(cssLeaveCodeEdit)).sendKeys(configLeaveAdmin.getProperty("EDIT_CODE"));

//       Enter the description
       driver.findElement(By.cssSelector(cssLeaveDescEdit)).sendKeys(Keys.CONTROL + "a");
       driver.findElement(By.cssSelector(cssLeaveDescEdit)).sendKeys(Keys.DELETE);
       driver.findElement(By.cssSelector(cssLeaveDescEdit)).sendKeys(configLeaveAdmin.getProperty("EDIT_DESCRIPTION"));

//       Eheck/unchek the active box
       driver.findElement(By.cssSelector(cssLeaveTypeActive)).click();
//    	 Click on save and close button
       driver.findElement(By.xpath(xpathSaveAndCloseLT)).click();
       
       int flag = 0;
       do
       {
      	 try
      	 {
      		 if(driver.findElement(By.xpath("//td[contains(text(),'"+configLeaveAdmin.getProperty("EDIT_CODE")+"')]")).isDisplayed()){
      			 org.testng.Assert.assertTrue(true, "The value is added to table successfully");
      			 flag=1;
      			 break;
      		 }
      	 }
      	 catch(Exception e){
      		 driver.findElement(By.xpath(xpathNextButtonLeaveType)).click();
      	 }
       }while(driver.findElement(By.xpath(xpathNextButtonLeaveType)).isEnabled());
       if(flag != 1){
      	 org.testng.Assert.assertTrue(false,"The value is not added");
       }    	 
     }
     
*//**************** TEST TO DELETE LEAVE TYPE ***************//*
//     @Test(groups="FunctionalTest",dependsOnMethods={"editLeaveType"})
     public  void deleteLeaveType(){
//    	 Select the required records
    	 driver.findElement(By.xpath("//td[contains(text(),'"+configLeaveAdmin.getProperty("EDIT_CODE")+"')]/..//input[@id='Check']")).click();

//    	 Click on delete button
    	 driver.findElement(By.cssSelector(cssDeleteLeaveType)).click();
    	 
//    	 Click on ok button
    	 driver.switchTo().alert().accept();
    	 
    	 if(driver.findElement(By.xpath(xpathMessage)).isDisplayed()){
    		 org.testng.Assert.assertTrue(true,"value deleted successfully");
    	 }
    	 else
    	 {
    		 org.testng.Assert.assertTrue(false,"value is not deleted");
    	 }
    	 login.LogoutTest(driver);
     }
     
*//******************* TEST FOR LEAVE ATTRIBUTE ********************//*
     @Test(groups="FunctionalTest")
     public void leaveAttribute()
     {
    	 
    	 System.out.println("******************* Entering Leave Attribute *******************");
// To get Username and password form property file   	 
     	username=config.getProperty("USERNAME");
     	password=config.getProperty("PASSWORD");
     	System.out.println("username -> "+username+"password->" + password);
 // Login to the application with required role    	 
          try{
        	  
              login.LoginTest(driver,username,password);
              
 // Navigate to "Leave Admin page" 
              WebDriverWait driverWait = new WebDriverWait(driver, explicitTimeout);
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssPWAdminMenu)));
              
              Actions act = new Actions(driver);
              WebElement adminMenu = driver.findElement(By.cssSelector(cssPWAdminMenu));
              act.moveToElement(adminMenu).perform();
              driver.findElement(By.xpath(xpathSeeAllSubMenu)).click();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveAdmin)));
              driver.findElement(By.cssSelector(cssLeaveAdmin)).click();
              
//            Click on leave type link
               driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveType)));
              driver.findElement(By.cssSelector(cssLeaveType)).click();
              int activeLeaveType = 0;
              int actualActiveLeaveType = 0;
              int countLeaveTypeList = 0;
	          while(true)
	          {
	        	  try 
           	 	{
					Thread.sleep(1000);
				}
           	 	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	activeLeaveType = driver.findElements(By.xpath("//td[text()='Yes']")).size();
           		actualActiveLeaveType = actualActiveLeaveType+activeLeaveType;
           		if(driver.findElements(By.xpath(xpathNextButtonLeaveType)).size()>0)
           		{
           			driver.findElement(By.xpath(xpathNextButtonLeaveType)).click();
           		}
           		else
           		{
           			break;
           		}
            }
//            System.out.println("actualActiveLeaveType->"+actualActiveLeaveType);
////////////////////////////////////// LEAVE ATTRIBUTE ///////////////////////////////////////////            
//              Click on leave attribute link.
             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveAttribute)).click();
              
//              Click on drop down list of leave type.
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              countLeaveTypeList = driver.findElements(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span")).size();

              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();

//              Enter the days in the expiration period text box.
//              System.out.println("countLeaveTypeList->"+countLeaveTypeList);  
              if((actualActiveLeaveType + 1) == countLeaveTypeList)
              {
            	  Assert.assertTrue(true,"The Count Matched");
              }
              else
              {
            	  Assert.assertFalse(false, "The Count Not Matched");
              }
             
              if(driver.findElement(By.id(idExpirationPeriodTextboxLeaveAttribute)).isDisplayed())
              {
            	  System.out.println("PASS Leave Attribute:Mandatory field present");
            	  Assert.assertTrue(true,"Mandatory field present");
              }
              else
              {	
            	  System.out.println("FAIL Leave Attribute:Mandatory field not present");
            	  Assert.assertFalse(false,"Mandatory field not present");
              }
              
              driver.findElement(By.id(idExpirationPeriodTextboxLeaveAttribute)).sendKeys("@@");
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(classErrorLeaveAttribute)));
              if(driver.findElement(By.className(classErrorLeaveAttribute)).isDisplayed())
              {
            	  System.out.println("PASS Leave Attribute:Cannot enter metacharecter");
            	  Assert.assertTrue(true,"Cannot enter metacharecter");
              }
              else
              {
            	  System.out.println("FAIL Leave Attribute:Entered metacharecter");
            	  Assert.assertFalse(false,"Entered metacharecter");
              }

//////////////////////////////////// SETTING APPLICABLE TO ///////////////////////////////////              
//              Click on drop down list of applicable to.
//			  Choose the required gender from the list.
              System.out.println("******************* Entering SETTING APPLICABLE TO *******************");
              
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathApplicableToButtonLeaveAttribute)));
              driver.findElement(By.xpath(xpathApplicableToButtonLeaveAttribute)).click();
              List<WebElement> webElemApplicableTo = driver.findElements(By.xpath("//label[contains(text(),'Applicable To')]/../..//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span"));
              for(int i=0;i<webElemApplicableTo.size();i++)
              {	
            	  WebElement a = webElemApplicableTo.get(i);
            	  String menuList = a.getText();
            	  if((menuList.equalsIgnoreCase("All")) || menuList.equalsIgnoreCase("Male") || menuList.equalsIgnoreCase("Female"))
            	  {
            		  System.out.println("PASS SETTING APPLICABLE TO:Option "+menuList+" Present");
            		  Assert.assertTrue(true, "Option "+menuList+" Present");
            	  }
            	  else
            	  {	
            		  System.out.println("PASS SETTING APPLICABLE TO:Options not Present");
            		  Assert.assertFalse(false, "Option"+menuList+"Not Present");
            	  }
              }
              
              driver.findElement(By.xpath("//label[contains(text(),'Applicable To')]/../..//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'Male')]")).click();
//////////////////////////////////// SET MAX NO OF DAYS ALLOWED ///////////////////////////////             
//              Enter the days in the maxmium no of days allowed text box.
              System.out.println("******************* Entering SET MAX NO OF DAYS ALLOWED *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(idDaysAllowedTextboxLeaveAttribute)));
              try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              driver.findElement(By.id(idDaysAllowedTextboxLeaveAttribute)).sendKeys("123456");
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              
              if(driver.findElement(By.cssSelector("div.inlineError")).isEnabled())
              {
            	 System.out.println("PASS SET MAX NO OF DAYS ALLOWED:Cannot exceed the day limit");
            	 Assert.assertTrue(true,"Cannot exceed the day limit");
              }
              else
              {
            	  System.out.println("FAIL SET MAX NO OF DAYS ALLOWED:Exceeded day limit");
            	  Assert.assertFalse(false, "Exceeded day limit");
              }
/////////////////////////////////// SET PAST DAYS APPLICATION //////////////////////////////
              
////////////////////////////////// SET LONG LEAVE /////////////////////////////////////////
              
///////////////////////////////// SET NOTICE PERIOD /////////////////////////////////              
//              select  yes/no radio buttons of allowed in notice period.
              System.out.println("******************* Entering SET NOTICE PERIOD *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(idNoticePeriod_NoRadioLeaveAttribute)));
              driver.findElement(By.id(idNoticePeriod_NoRadioLeaveAttribute)).click();
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              if(driver.findElement(By.cssSelector("div.inlineError")).isEnabled())
              {
            	 System.out.println("PASS SET NOTICE PERIOD:Enter Leave Type");
            	 Assert.assertTrue(true,"Enter Leave Type");
              }
              else
              {
            	  System.out.println("FAIL SET NOTICE PERIOD: Enter Leave Type does not exist");
            	  Assert.assertFalse(false, "Enter Leave Type does not exist");
              }
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(idNoticePeriod_NoRadioLeaveAttribute)));
              driver.findElement(By.id(idNoticePeriod_NoRadioLeaveAttribute)).click();
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              if(driver.findElement(By.cssSelector("div.inlineError")).isEnabled())
              {
            	 System.out.println("PASS SET NOTICE PERIOD:Enter Leave Type");
            	 Assert.assertTrue(true,"Enter Leave Type");
              }
              else
              {
            	  System.out.println("FAIL SET NOTICE PERIOD: Enter Leave Type does not exist");
            	  Assert.assertFalse(false, "Enter Leave Type does not exist");
              }

/////////////////////////////////// SET APPROVER LEVEL /////////////////////////////////            
//              Click on drop down list of approver level.
//              Choose any number from the list.
              System.out.println("******************* Entering SET APPROVER LEVEL *******************");
              driver.navigate().refresh();
              
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
    				Thread.sleep(1000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
                driver.findElement(By.id(idPastDaysApplicationLeaveAttribute)).sendKeys("1");
                
              driver.findElement(By.xpath(xpathApproverLevelButtonLeaveAttribute)).click();
              List<WebElement> listApproverLevel = driver.findElements(By.xpath("//label[text()='Approver Level']/../..//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span"));
              for(WebElement labelApproverLevel : listApproverLevel)
              {
            	  if((Integer.parseInt(labelApproverLevel.getText()) > 0) && (Integer.parseInt(labelApproverLevel.getText()) < 10)){
            		  Assert.assertTrue(true, "value "+labelApproverLevel.getText()+" is present");
            		  System.out.println("PASS SET APPROVER LEVEL:value "+labelApproverLevel.getText()+" is present");
            	  }
            	  else{
            		  Assert.assertFalse(false, "value "+labelApproverLevel.getText()+"is not present");
            		  System.out.println("FAIL SET APPROVER LEVEL:value not present");
            	  }
              }
              driver.findElement(By.xpath("//label[text()='Approver Level']/../..//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'2')]")).click();

///////////////////////////////////////// SET MIN NO OF DAYS ALLOWED ///////////////////////////////             
//              Enter days in minimum no of days allowed text box
              System.out.println("******************* Entering SET MIN NO OF DAYS ALLOWED *******************");   
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
              int minDaysValue = Integer.parseInt(driver.findElement(By.id(idMinDaysTextboxLeaveAttribute)).getAttribute("value"));
              if(minDaysValue == 1)
              {
            	  System.out.println("PASS SET MIN NO OF DAYS ALLOWED:minDaysValue"+minDaysValue);
            	  Assert.assertTrue(true,"Minimum value is 1");
              }
              else
              {
            	  System.out.println("FAIL SET MIN NO OF DAYS ALLOWED:Minimum value is not 1");
            	  Assert.assertFalse(false, "Minimum value is not 1");
              }
//              Alphabets and special charecters validation not performed

/////////////////////////////////// SET MIN ADVANCED NOTIFICATION //////////////////////////////////
//              Enter the days in minimum advanced notification text field.
              System.out.println("******************* Entering  SET MIN ADVANCED NOTIFICATION *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
              driver.findElement(By.id(idAdvancedNotificationLeaveAttribute)).sendKeys("2");
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isEnabled())
              {
            	  System.out.println("PASS SET MIN ADVANCED NOTIFICATION:Added the data");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL SET MIN ADVANCED NOTIFICATION:Data not added");
            	  Assert.assertFalse(false, "Data not added");
              }
            //DOUBT  
//  			  4.It should allow when employee apply leave before 'n'  no of days entered in minimum advanced notification text field excluding the applied date.
//              5.It should not allow when employee apply leave less than before 'n' no of days entered in the minimum advanced notification feild excluding the applied date. 
        

///////////////////////////////////// SET IS RESTRICTED ////////////////////////////////
//              Select yes/no radio buttons of Is restricted.
              System.out.println("******************* Entering  SET IS RESTRICTED *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
              driver.findElement(By.id(idRestricted_YesRadioLeaveAttribute)).click();
              driver.findElement(By.id(idRestricted_NoRadioLeaveAttribute)).click();
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isEnabled())
              {
            	  System.out.println("PASS SET IS RESTRICTED:Added the data");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL SET IS RESTRICTED:Data not added");
            	  Assert.assertFalse(false, "Data not added");
              }
              
///////////////////////////////////// SET AUTO CREDIT ///////////////////////////////////////
//              Select yes/no radio buttons of Auto credit.
              System.out.println("******************* Entering SET AUTO CREDIT *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
              if(driver.findElement(By.id(idAutoCredit_YesRadioLeaveAttribute)).isEnabled()){
            	  Assert.assertTrue(true, "Radio Exist");
              }
              else{
            	  Assert.assertFalse(true, "Radio does not exist");
              }
              driver.findElement(By.id(idAutoCredit_YesRadioLeaveAttribute)).click();
              driver.findElement(By.id(idAutoCredit_NoRadioLeaveAttribute)).click();
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isEnabled())
              {
            	  System.out.println("PASS SET AUTO CREDIT:Added the data");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL SET AUTO CREDIT:Data not added");
            	  Assert.assertFalse(false, "Data not added");
              }
              
//////////////////////////////////// SET ALLOW HALF DAY ////////////////////////////////////
//              select yes/no radio buttons of Allow half day
              System.out.println("******************* Entering SET ALLOW HALF DAY *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
              if(driver.findElement(By.id(idAllowHalfDay_YesRadioLeaveAttribute)).isEnabled()){
            	  System.out.println("PASS SET ALLOW HALF DAY:Radio Exist");
            	  Assert.assertTrue(true, "Radio Exist");
              }
              else{
            	  System.out.println("FAIL SET ALLOW HALF DAY:Radio does not exist");
            	  Assert.assertFalse(true, "Radio does not exist");
              }
              driver.findElement(By.id(idAllowHalfDay_NoRadioLeaveAttribute)).click();
              driver.findElement(By.id(idAllowHalfDay_YesRadioLeaveAttribute)).click();
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isEnabled())
              {
            	  System.out.println("PASS SET ALLOW HALF DAY:Added the data");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL SET ALLOW HALF DAY:Data not added");
            	  Assert.assertFalse(false, "Data not added");
              }
              
///////////////////////////////// SET LOSS OF PAY //////////////////////////////
              System.out.println("******************* Entering SET LOSS OF PAY *******************");
//              Select yes/no radio buttons of Is loss of pay.
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
              if(driver.findElement(By.id(idLossOfPay_YesRadioLeaveAttribute)).isEnabled()){
            	  System.out.println("PASS SET LOSS OF PAY:Radio Exist");
            	  Assert.assertTrue(true, "Radio Exist");
              }
              else{
            	  System.out.println("FAIL SET LOSS OF PAY:Radio Exist");
            	  Assert.assertFalse(true, "Radio does not exist");
              }
              driver.findElement(By.id(idLossOfPay_YesRadioLeaveAttribute)).click();
              driver.findElement(By.id(idLossOfPay_NoRadioLeaveAttribute)).click();
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isEnabled())
              {
            	  System.out.println("PASS SET LOSS OF PAY:Added the data");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL SET LOSS OF PAY:Data not added");
            	  Assert.assertFalse(false, "Data not added");
              }
              driver.navigate().refresh();
              
/////////////////////////////////////// SET LEAVE APPLICABILITY ///////////////////////////////////
              System.out.println("******************* Entering SET LEAVE APPLICABILITY *******************");
//              Click on drop down list of leave applicability from leave attribute page.
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
              driver.findElement(By.xpath(xpathLeaveApplicabilityButtonLeaveAttribute)).click();
              List<WebElement> listLeaveApplicability = driver.findElements(By.xpath("//label[contains(text(),'Leave Applicability')]/../..//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span"));
              for(WebElement labelLeaveApplicability : listLeaveApplicability)
              {
            	  if((labelLeaveApplicability.getText().equalsIgnoreCase("Earned")) || (labelLeaveApplicability.getText().equalsIgnoreCase("Accrued")) || (labelLeaveApplicability.getText().equalsIgnoreCase("Advance"))){
            		  Assert.assertTrue(true, "value "+labelLeaveApplicability.getText()+" is present");
            		  System.out.println("PASS SET LEAVE APPLICABILITY:value "+labelLeaveApplicability.getText()+" is present");
            	  }
            	  else{
            		  System.out.println("FAIL SET LEAVE APPLICABILITY:value nt present");
            		  Assert.assertFalse(false, "value "+labelLeaveApplicability.getText()+" is not present");
            	  }
              }
//            If Earned is selected
//            If Accrued is selected
              
//            If advance is selected
//            Enter the months in advance months text field.
              driver.findElement(By.xpath("//label[contains(text(),'Leave Applicability')]/../..//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'Advance')]")).click();
              if(driver.findElement(By.id(idAdvanceMonthsLeaveAttribute)).isEnabled()){
            	  System.out.println("PASS SET LEAVE APPLICABILITY:Advance Months present");
            	  Assert.assertTrue(true,"Advance Months present");
              }
              else{
            	  System.out.println("FAIL SET LEAVE APPLICABILITY:Advance Months not present");
            	  Assert.assertFalse(false, "Advance month not present");
              }
              driver.findElement(By.id(idAdvanceMonthsLeaveAttribute)).clear();
              driver.findElement(By.id(idAdvanceMonthsLeaveAttribute)).sendKeys("13");
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              if(driver.findElement(By.className(classErrorLeaveAttribute)).isEnabled())
              {
            	  System.out.println("PASS SET LEAVE APPLICABILITY:Data not between 1-12");
            	  Assert.assertTrue(true, "Data not between 1-12");
              }
              else{
            	  System.out.println("FAIL SET LEAVE APPLICABILITY:Data Added");
            	  Assert.assertFalse(false, "Data added");
              }
              driver.findElement(By.id(idAdvanceMonthsLeaveAttribute)).clear();
              driver.findElement(By.id(idAdvanceMonthsLeaveAttribute)).sendKeys("3");
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isEnabled())
              {
            	  System.out.println("PASS SET LEAVE APPLICABILITY:Data between 1-12");
            	  Assert.assertTrue(true, "Data between 1-12");
              }
              else{
            	  System.out.println("FAIL SET LEAVE APPLICABILITY:Datanot added");
            	  Assert.assertFalse(false, "Data not added");
              }
              driver.navigate().refresh();
              
//////////////////////////////// SET LEAVE CARRY FORWARD ///////////////////////////////////
              System.out.println("******************* Entering SET LEAVE CARRY FORWARD *******************");
//              Select yes/no radio buttons of carry forward.
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
              if(driver.findElement(By.id(idCarryForward_YesRadioLeaveAttribute)).isEnabled()){
            	  System.out.println("PASS SET LEAVE CARRY FORWARD:Radio exist");
            	  Assert.assertTrue(true, "Radio Exist");
              }
              else{
            	  System.out.println("FAIL SET LEAVE CARRY FORWARD:Radio Does not exist");
            	  Assert.assertFalse(true, "Radio does not exist");
              }
              driver.findElement(By.id(idCarryForward_NoRadioLeaveAttribute)).click();
              Thread.sleep(1000);
              if(driver.findElement(By.id(idYearlyCarryForwardLeaveAttribute)).isDisplayed()){
            	  System.out.println("FAIL SET LEAVE CARRY FORWARD:Textbox Displayed");
            	  Assert.assertFalse(false, "Text box displayed");
              }
              else{
            	  Assert.assertTrue(true, "Text box not displayed");
            	  System.out.println("PASS SET LEAVE CARRY FORWARD:text not diaplayed");
              }
//              Enter the days in text box of yearly carry forward limit.
              driver.findElement(By.id(idCarryForward_YesRadioLeaveAttribute)).click();
              Thread.sleep(1000);
              if(driver.findElement(By.id(idYearlyCarryForwardLeaveAttribute)).isDisplayed()){
            	  Assert.assertTrue(true, "Text box displayed");
            	  System.out.println("PASS SET LEAVE CARRY FORWARD:text diaplayed");
              }
              else{
            	  System.out.println("FAIL SET LEAVE CARRY FORWARD:Text not displayed");
            	  Assert.assertFalse(false, "Text box not displayed");
              }
              driver.findElement(By.id(idCarryForward_YesRadioLeaveAttribute)).click();
              Thread.sleep(1000);
              System.out.println("idYearlyCarryForwardLeaveAttribute "+idYearlyCarryForwardLeaveAttribute+"data "+configLeaveAdmin.getProperty("YEARLY_CARRYFORWARD"));
              driver.findElement(By.id(idYearlyCarryForwardLeaveAttribute)).clear();
              driver.findElement(By.id(idYearlyCarryForwardLeaveAttribute)).sendKeys(configLeaveAdmin.getProperty("YEARLY_CARRYFORWARD"));
//            Enter the days in text box of overall carry forward limit.
              System.out.println("idOverAllCarryForwardLeaveAttribute "+idOverAllCarryForwardLeaveAttribute+"data "+configLeaveAdmin.getProperty("OVERALL_CARRYFORWARD"));
              driver.findElement(By.id(idOverAllCarryForwardLeaveAttribute)).clear();
              driver.findElement(By.id(idOverAllCarryForwardLeaveAttribute)).sendKeys(configLeaveAdmin.getProperty("OVERALL_CARRYFORWARD"));
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              Thread.sleep(1000);
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isDisplayed())
              {
            	  System.out.println("PASS SET LEAVE CARRY FORWARD:DAta added");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL SET LEAVE CARRY FORWARD:Datanot added");
            	  Assert.assertFalse(false, "Data not added");
              }
              
///////////////////////////// SET LEAVE RESTRICTION //////////////////////////
//              Select the cycle from the restriction cycle.
              System.out.println("******************* Entering SET LEAVE RESTRICTION *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              Thread.sleep(1000);
              driver.findElement(By.xpath(xpathRestrictionCycleButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//label[contains(text(),'Restriction Cycle')]/../..//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[text()='"+configLeaveAdmin.getProperty("RESTRICTION_CYCLE")+"']")).click();
//              Enter the days in edit box of no of leaves.
              driver.findElement(By.id(idNoOfLeaveLeaveAttribute)).clear();
              driver.findElement(By.id(idNoOfLeaveLeaveAttribute)).sendKeys(configLeaveAdmin.getProperty("NO_OF_LEAVES"));
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              Thread.sleep(1000);
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isDisplayed())
              {
            	  System.out.println("PASS  SET LEAVE RESTRICTION:Added the data");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL Added the data:Data not added");
            	  Assert.assertFalse(false, "Data not added");
              }
              
////////////////////////////////////// ESCALATION ///////////////////////////////////
//              Enter the days in edit box of escalations seperated by comma.
              System.out.println("******************* Entering ESCALATION *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              Thread.sleep(1000);
              driver.findElement(By.id(idEscalationsTextboxLeaveAttribute)).clear();
              driver.findElement(By.id(idEscalationsTextboxLeaveAttribute)).sendKeys("3");
//              Enter the days in edit box of before no of days
              driver.findElement(By.id(idNoOfDaysBeforeTextboxLeaveAttribute)).clear();
              driver.findElement(By.id(idNoOfDaysBeforeTextboxLeaveAttribute)).sendKeys("1");
//              Select the  approve radio button of action.
              driver.findElement(By.id(idApproveRadioLeaveAttribute)).click();
//              Select the  reject radio button of action.
//              Select the  NO ACTION  radio button of action.
//              Select yes\no radio buttons of INCLUDE WEEK-OFF.
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              Thread.sleep(1000);
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isDisplayed())
              {
            	  System.out.println("PASS ESCALATION:Added data");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL ESCALATION:Data nont added");
            	  Assert.assertFalse(false, "Data not added");
              }
              
//////////////////////////// LEAVE ENCASHMENT //////////////////////////////////
//              Select no, radio button of Applicable for encashment.
              System.out.println("******************* Entering LEAVE ENCASHMENT *******************");
              driver.navigate().refresh();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVETYPE")+"')]")).click();
              Thread.sleep(1000);
              driver.findElement(By.id(idEncashment_NoRadioLeaveAttribute)).click();
//              Select yes, radio button of Applicable for encashment.
              driver.findElement(By.id(idEncashment_YesRadioLeaveAttribute)).click();
//              Enter the days in edit box of min allow days.
              driver.findElement(By.id(idMinDaysAllowedTextboxLeaveAttribute)).clear();
              driver.findElement(By.id(idMinDaysAllowedTextboxLeaveAttribute)).sendKeys("5");
//              Enter the days in edit box of max allow days.
              driver.findElement(By.id(idMaxDaysAllowedTextboxLeaveAttribute)).clear();
              driver.findElement(By.id(idMaxDaysAllowedTextboxLeaveAttribute)).sendKeys("10");
//              Enter the days in edit box of exclude leave.
              driver.findElement(By.id(idExcludeLeaveTextboxLeaveAttribute)).clear();
              driver.findElement(By.id(idExcludeLeaveTextboxLeaveAttribute)).sendKeys("1");
//              Click on drop down list of Encashment Cycle.
              driver.findElement(By.xpath(xpathEncashmentCycleButtonLeaveAttribute)).click();
              List<WebElement> listEncashmentCycle = driver.findElements(By.xpath("//label[contains(text(),'Encashment Cycle')]/../..//ul[contains(@class,'ui-multiselect-checkboxes')]//span"));
              for(WebElement labelEncashmentCycle : listEncashmentCycle){
            	  if((labelEncashmentCycle.getText().equalsIgnoreCase("Yearly")) || labelEncashmentCycle.getText().equalsIgnoreCase("Quarterly") || labelEncashmentCycle.getText().equalsIgnoreCase("Monthly"))
            	  {
            		  System.out.println("PASS LEAVE ENCASHMENT:Option"+labelEncashmentCycle.getText()+"Present");
            		  Assert.assertTrue(true, "Option "+labelEncashmentCycle.getText()+" Present");
            	  }
            	  else
            	  {
            		  System.out.println("FAIL LEAVE ENCASHMENT:option not present");
            		  Assert.assertFalse(false, "Option"+labelEncashmentCycle.getText()+"Not Present");
            	  }
              }
//              Select the required option from the list.
              driver.findElement(By.xpath("//label[contains(text(),'Encashment Cycle')]/../..//ul[contains(@class,'ui-multiselect-checkboxes')]//span[contains(text(),'Yearly')]")).click();
//              Click on save button.
              driver.findElement(By.id(idSaveButonLeaveAttribute)).click();
              Thread.sleep(1000);
              if(driver.findElement(By.cssSelector(cssSuccessMessageLeaveAttribute)).isDisplayed())
              {
            	  System.out.println("PASS LEAVE ENCASHMENT:Data added");
            	  Assert.assertTrue(true, "Added the data");
              }
              else{
            	  System.out.println("FAIL LEAVE ENCASHMENT:Data not added");
            	  Assert.assertFalse(false, "Data not added");
              }
//              Click on cancel button.
              login.LogoutTest(driver);
          }
          catch (SeleniumException e){
        	  System.out.println("Exception");
        	  e.printStackTrace();
          } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     
//************************ test to check default value of leave attribute page *********************     
     
//     @Test(groups="FunctionalTest")
     public void defaultValValidationLeaveAttribute(){
    	// To get Username and password form property file   	 
     	username=config.getProperty("USERNAME");
     	password=config.getProperty("PASSWORD");
     	System.out.println("username -> "+username+"password->" + password);
 // Login to the application with required role    	 
          try{
         	 
              login.LoginTest(driver,username,password);
              
 // Navigate to "Leave Admin page" 
              WebDriverWait driverWait = new WebDriverWait(driver, explicitTimeout);
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssPWAdminMenu)));
              
              Actions act = new Actions(driver);
              WebElement adminMenu = driver.findElement(By.cssSelector(cssPWAdminMenu));
              act.moveToElement(adminMenu).perform();
              driver.findElement(By.xpath(xpathSeeAllSubMenu)).click();
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveAdmin)));
              driver.findElement(By.cssSelector(cssLeaveAdmin)).click();
              
//              Click on leave type link
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveType)));
              driver.findElement(By.cssSelector(cssLeaveType)).click();
              
//              Click on add button
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssAddLeaveType)));
              driver.findElement(By.cssSelector(cssAddLeaveType)).click();
              
//              Enter the name in name text field
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeEdit)));
              driver.findElement(By.cssSelector(cssLeaveTypeEdit)).sendKeys(configLeaveAdmin.getProperty("LEAVE_TYPE"));
              
//              Enter the code in the code text field
              driver.findElement(By.cssSelector(cssLeaveCodeEdit)).sendKeys(configLeaveAdmin.getProperty("CODE"));
//              Enter the description
              driver.findElement(By.cssSelector(cssLeaveDescEdit)).sendKeys(configLeaveAdmin.getProperty("DESCRIPTION"));
              
//              Click on save and close button 
              driver.findElement(By.xpath(xpathSaveAndCloseLT)).click();
              
//              navigating to leave attribute page
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveAttribute)).click();
              
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)));
              driver.findElement(By.cssSelector(cssLeaveTypeButtonLeaveAttribute)).click();
              driver.findElement(By.xpath("//td[@class='formLabel appliedFilter']//div/ul[contains(@class,'ui-multiselect-checkboxes')]/li//span[contains(text(),'"+configLeaveAdmin.getProperty("LEAVE_TYPE")+"')]")).click();
              try {
  				Thread.sleep(1000);
  			  } catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			  }
//            validation of "Max. No. of Days Allowed" "Past Days Application" and "Min. No. of Days Allowed"
              if((Integer.parseInt(driver.findElement(By.id(idDaysAllowedTextboxLeaveAttribute)).getAttribute("value")) == 0) && (Integer.parseInt(driver.findElement(By.id(idPastDaysApplicationLeaveAttribute)).getAttribute("value")) == 0) && (Integer.parseInt(driver.findElement(By.id(idMinDaysTextboxLeaveAttribute)).getAttribute("value")) == 1)){
            	  System.out.println("pass");
            	  Assert.assertTrue(true,"Default value is matching");
              }
              else{
            	  Assert.assertFalse(false, "Default value is not matching");
              }
              
              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveType)));
              driver.findElement(By.cssSelector(cssLeaveType)).click();
              Thread.sleep(1000);
              do
              {
             	 try
             	 {
             		 if(driver.findElement(By.xpath("//td[contains(text(),'"+configLeaveAdmin.getProperty("LEAVE_TYPE")+"')]")).isDisplayed()){
             			 org.testng.Assert.assertTrue(true, "The value is added to table successfully");
             			 break;
             		 }
             	 }
             	 catch(Exception e){
             		 driver.findElement(By.xpath(xpathNextButtonLeaveType)).click();
             	 }
              }while(driver.findElement(By.xpath(xpathNextButtonLeaveType)).isEnabled());

//            Deleting the leave type
              driver.findElement(By.xpath("//td[contains(text(),'"+configLeaveAdmin.getProperty("LEAVE_TYPE")+"')]/..//input[@id='Check']")).click();

//         	 Click on delete button
         	 driver.findElement(By.cssSelector(cssDeleteLeaveType)).click();
         	 
//         	 Click on ok button
         	 driver.switchTo().alert().accept();
         	 
         	 if(driver.findElement(By.xpath(xpathMessage)).isDisplayed()){
         		 org.testng.Assert.assertTrue(true,"value deleted successfully");
         	 }
         	 else
         	 {
         		 org.testng.Assert.assertTrue(false,"value is not deleted");
         	 }
         	 login.LogoutTest(driver);
          }
          catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
     }
//     @Test(groups="FunctionalTest")
     public void addCreditPolicy(){
// Login to the application with required role    	 
         try{
        	 
             login.LoginTest(driver,username,password);
             
// Navigate to "Leave Admin page" 
             WebDriverWait driverWait = new WebDriverWait(driver, explicitTimeout);
             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssPWAdminMenu)));
             
             Actions act = new Actions(driver);
             WebElement adminMenu = driver.findElement(By.cssSelector(cssPWAdminMenu));
             act.moveToElement(adminMenu).perform();
             driver.findElement(By.xpath(xpathSeeAllSubMenu)).click();
             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLeaveAdmin)));
             driver.findElement(By.cssSelector(cssLeaveAdmin)).click();

     }
     catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   	}        
  }
}
*/