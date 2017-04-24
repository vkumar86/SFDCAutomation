package test.ATMScenarios;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.thoughtworks.selenium.SeleniumException;

import library.GeneralFunc;
import library.InitialSetUp;
import library.LogInFunc;
import objectRepo.ATMOR;
import objectRepo.LoginScenarioOR;
import reporting.TestLogger;


 public class SFDCaccountcreation extends LogInFunc implements ATMOR,LoginScenarioOR {
	// Variable or Object declaration
	    String username;
		String password;
		LogInFunc logObj = new LogInFunc();
		WebDriverWait driverWait;
		private int loginTimes = 0;
		
		// Methods declaration
		public SFDCaccountcreation() throws Exception {
			super();
			String loginFN = "LoginData.xlsx";
			String loginSN = "Login";
			
			
			int rowNum = 1;
			
			username = logObj.getExcelValue("LoginData.xlsx", "Login", "Username", 1);
			password = logObj.getExcelValue("LoginData.xlsx", "Login", "Password", 1);
			
		}

		private void login() throws InterruptedException {
			if (driver.toString().contains("null")) {
				try {			
					InitialSetUp brwObj = new InitialSetUp();
					brwObj.instantiateWebDriver();
				    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (loginTimes == 0) {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				if (driver.findElements(logObj.buildLocator(logUserName)).size() != 0) {
					logObj.LoginTest(driver, username, password);

				}
			}
		}
 
		// TC Description : Verify user is able to create SFDC Account for BSD Member
		@Test(groups = "FunctionalTest", testName = "CreateAcc_SFDC_01", enabled = true, description = "(ATM, Account Creation for BSD member) - Verify  user able to create new account")
		public void CreateAcc_SFDC_01() throws Exception {
			try {
				
				this.login();
				String fileName = "ATM.xlsx";
				String sheetName = "CreateAcc_SFDC_01";
				int rowNum = 1;
				Thread.sleep(5000);
				driver.navigate().to(BSDUserURL);
				Thread.sleep(5000);
				logObj.buildDriverElement(loginsso).click();
				driver.navigate().to(ProfileURL);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				logObj.buildDriverElement(New).click();
				WebDriverWait driverWait;
				driverWait = new WebDriverWait(driver,explicitTimeout);
				driverWait.until(ExpectedConditions.elementToBeClickable(logObj.buildLocator(Continue)));
				logObj.isElementExists(logObj.buildDriverElement(Continue), "Continue button exists");				
				logObj.buildDriverElement(Continue).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//String AccountNametxt=RandomStringUtils.randomAlphabetic(5);
				String AccountName=logObj.getExcelValue(fileName, sheetName, "AccountName", rowNum);
		        logObj.buildDriverElement(AccountName).sendKeys(AccountName);
		        Select s=new Select(logObj.buildDriverElement(LeadSource));
		        s.selectByVisibleText("ATE");
		        String Country=logObj.getExcelValue("ATM.xlsx", "CreateAcc_SFDC_01", "Country", 1);
		        Select s2=new Select(logObj.buildDriverElement(MainCountry));
		        s2.selectByVisibleText(Country);
		        logObj.buildDriverElement(MainCountry).sendKeys(Country);
		        String Street=logObj.getExcelValue(fileName, sheetName, "Street", rowNum);
		        logObj.buildDriverElement(MainStreet).sendKeys(Street);
		        String city=logObj.getExcelValue(fileName, sheetName, "city", rowNum);
		        logObj.buildDriverElement(MainCity).sendKeys(city);
		        String state=logObj.getExcelValue(fileName, sheetName, "state", rowNum);
		        Select s1=new Select(logObj.buildDriverElement(MainState));
		        s1.selectByVisibleText(state);
		        logObj.buildDriverElement(MainState).sendKeys(state);
		        String zip=logObj.getExcelValue(fileName, sheetName, "zip", rowNum);
		        logObj.buildDriverElement(MainZip).sendKeys(zip);
		        String Affi=logObj.getExcelValue(fileName, sheetName, "Affi", rowNum);
		        logObj.buildDriverElement(NicheAffiliations).sendKeys(Affi);
		        Thread.sleep(5000);
		        logObj.buildDriverElement(AddAffiliations).click();
		        Thread.sleep(5000);
		        logObj.buildDriverElement(Save).click();
		        Thread.sleep(5000);      
	        
			} catch (SeleniumException e) {
				e.printStackTrace();
			}
			
		}
		// TC Description : Verify BSD_PrimaryProducer added to newly created account
		@Test(groups = "FunctionalTest", testName = "CreateAcc_SFDC_02", enabled = true, description = "(ATM, BSD MemberMapping) - Verify BSD_PrimaryProducer added to newly created account")
		public void CreateAcc_SFDC_02() throws Exception {
			try {
				
				this.login();
				String fileName = "ATM.xlsx";
				String sheetName = "CreateAcc_SFDC_02";
				int rowNum = 1;
				WebElement userid1=logObj.buildDriverElement(BSD_PrimaryProducer);
				String BSD_Account_Name=logObj.getExcelValue(fileName, sheetName, "BSD_Account_Name", rowNum);
				TestLogger.log("Info", "Verifying BSD_PrimaryProducer added to account or not");
				logObj.verifyText(userid1,BSD_Account_Name);
				WebElement userid=logObj.buildDriverElement(Acc_Team_Member_UserID);
				logObj.verifyText(userid,BSD_Account_Name);
					      
	        
			} catch (SeleniumException e) {
				e.printStackTrace();
			}
			
		}
		
		// TC Description : Verify BSD_PrimaryProducer added to newly created account
				@Test(groups = "FunctionalTest", testName = "CreateAcc_SFDC_03", enabled = true, description = "(ATM, Create Secondry BSD Member for SFDC account) - Verify adding new Secondry BSD Member for SFDC account")
				public void CreateAcc_SFDC_03() throws Exception {
					try {
						
						this.login();
						String fileName = "ATM.xlsx";
						String sheetName = "CreateAcc_SFDC_03";
						int rowNum = 1;
						logObj.buildDriverElement(New_Account_Team_Member).click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						String Secondary_BSD_Member_name=logObj.getExcelValue(fileName, sheetName, "SecondBSDname", rowNum);
						logObj.buildDriverElement(UserId).sendKeys(Secondary_BSD_Member_name);
						logObj.buildDriverElement(TeamMemberRole).sendKeys("BSD Producer");
						logObj.buildDriverElement(Save).click();
						Thread.sleep(5000);
						String Navigate_URL_SFDCID=logObj.buildDriverElement(SFDCID).getText();
				        driver.navigate().to("https://gallagher--stage.cs12.my.salesforce.com/"+Navigate_URL_SFDCID);
				        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				        
				        WebElement userid2=logObj.buildDriverElement(Second_BSD_Member);
				        logObj.verifyText(userid2,Secondary_BSD_Member_name);
				        
			        
					} catch (SeleniumException e) {
						e.printStackTrace();
					}
					
				}
	    		// TC Description:  verify the Primary Producer user in User and Group Sharing table
				@Test(groups = "FunctionalTest", testName ="CreateAcc_SFDC_04", enabled = true, description = "(ATM, Sharing) - Verify the primary producer name in User and Group sharing table is exists or not ")
				public void CreateAcc_SFDC_04() throws Exception{
					try{
					     this.login();
					     String fileName = "ATM.xlsx";
						 String sheetName = "PrimaryProducerInSharing";
						 int rowNum = 1;
						 logObj.buildDriverElement(Sharing1).click();
						 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						 String PrimaryProducer=logObj.getExcelValue(fileName, sheetName, "PrimaryProducerName", rowNum);
						 if(driver.findElement(By.partialLinkText(PrimaryProducer)).isDisplayed())
					     {
					    	  TestLogger.log("Info", PrimaryProducer +" is exists");
					     }
					     else{
					    	 TestLogger.log("Info", PrimaryProducer +" is not exists");

					     }
					     
					   } catch (SeleniumException e) {
							e.printStackTrace();	
				       }
					driver.navigate().back();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				}
				
				
		// TC Description : Verify BDS users/role in Sharing and SFDC Account Team 
		   @Test(groups = "FunctionalTest", testName = "CreateAcc_SFDC_05", enabled = true, description = "(ATM, Create Secondry BSD Member and Designations for SFDC account) - Verify adding new Secondry BSD Member/Role for SFDC account and Sharing")
				public void CreateAcc_SFDC_05() throws Exception {
					try {
						
						this.login();
						String fileName = "ATM.xlsx";
						String sheetName = "BSD_Members_And_Role";
						int rowNum;
						int rowCount = 16;
						logObj.buildDriverElement(New_Account_Team_Member).click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						for(rowNum =1; rowNum<=rowCount; rowNum++)
						{
						 String Secondary_BSD_Member_name=logObj.getExcelValue(fileName, sheetName, "UserId", rowNum);
						logObj.buildDriverElement(UserId).sendKeys(Secondary_BSD_Member_name);
						String Secondary_BSD_Member_Role=logObj.getExcelValue(fileName, sheetName, "TeamMemberRole", rowNum);
						logObj.buildDriverElement(TeamMemberRole).sendKeys(Secondary_BSD_Member_Role);
						
						    if(rowNum==rowCount)
						     {
						    	logObj.buildDriverElement(SaveBSD).click();
							    Thread.sleep(10000);
							    
						     }
						    else{
						    	logObj.buildDriverElement(SaveAndNewBSD).click();
							    Thread.sleep(1000);
						    }
						}
						String Navigate_URL_SFDCID=driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[2]/table/tbody/tr[3]/td[2]")).getText();
				        driver.navigate().to("https://gallagher--stage.cs12.my.salesforce.com/"+Navigate_URL_SFDCID);
				        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				        logObj.buildDriverElement(ATM_GoToListlink).click();
				        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				        TestLogger.log("Info", "Verified User/Role in Account Team Members");
				             for(rowNum=1;rowNum<=rowCount;rowNum++)
				             {
				            	 String get_userId = logObj.getExcelValue(fileName, sheetName, "UserId", rowNum);
				            	 String get_Role = logObj.getExcelValue(fileName, sheetName, "TeamMemberRole", rowNum);
				            	 if(driver.findElement(By.partialLinkText(get_userId)).isDisplayed())
				            	 {
				            		 TestLogger.log("Info", get_userId+" ("+get_Role+")" +" is exists");
				            	 }
				            	 else
				            	 {
				            		 TestLogger.log("Info", get_userId+" ("+get_Role+")" +" is not exists");
				            	 }
				            	   
				             }
				         driver.navigate().back(); 
				         TestLogger.log("Info", "Verified User/Role in SFDC Account Team");
				         logObj.buildDriverElement(SFDC_GoTOLink).click();
				         driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				             for(rowNum=1;rowNum<=rowCount;rowNum++)
			                  {
			            	     String get_userId = logObj.getExcelValue(fileName, sheetName, "UserId", rowNum);
			            	     String get_Role = logObj.getExcelValue(fileName, sheetName, "TeamMemberRole", rowNum);
			            	     if(driver.findElement(By.partialLinkText(get_userId)).isDisplayed())
			            	     {
			            		   TestLogger.log("Info", get_userId+" ("+get_Role+")" +" is exists");
			            	     }
			            	     else
			            	     {
			            		   TestLogger.log("Info", get_userId+" ("+get_Role+")" +" is not exists");
			            	     }
			            	   
			                  }  
				        driver.navigate().back();
				        TestLogger.log("Info", "Verified User/Role in User and Group Sharing");
                        logObj.buildDriverElement(Sharing1).click();
                        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			                for(rowNum=1;rowNum<=rowCount;rowNum++)
		                     {
		            	       String get_userId = logObj.getExcelValue(fileName, sheetName, "UserId", rowNum);
		            	       String get_Role = logObj.getExcelValue(fileName, sheetName, "TeamMemberRole", rowNum);
		            	       if(driver.findElement(By.partialLinkText(get_userId)).isDisplayed())
		            	       {
		            		    TestLogger.log("Info", get_userId+" ("+get_Role+")" +" is exists");
		            	       }
		            	       else
		            	       {
		            		    TestLogger.log("Info", get_userId+" ("+get_Role+")" +" is not exists");
		            	       }
		            	   
		                     }
			                driver.navigate().back();
			                } catch (SeleniumException e) {
						e.printStackTrace();
					}
					
				}
		   
		   //TC Description: Search the inactive userid in the ATM lookup table.
		   @Test(groups = "FunctionalTest", testName = "CreateAcc_SFDC_06", enabled = true, description = "(ATM) - Verify the Inactive UserId in the ATM LooKup table")
		      public void CreateAcc_SFDC_06() throws Exception
		      {
			     try
			     {
			    	 this.login();
			    	 String fileName = "ATM.xlsx";
			    	 String sheetName = "Inactive_userId";
			    	 int rowNum =1;
			    	 logObj.buildDriverElement(New_Account_Team_Member).click();
					 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					 String Search_UserID = logObj.getExcelValue(fileName, sheetName, "UserId", rowNum);
					 String parentHandle = driver.getWindowHandle();
					 driver.findElement(By.xpath("//span/a[contains(@id,'CF00N80000004g7aF_lkwgt')]")).click();
					 Thread.sleep(10000);
					 for(String winHandle : driver.getWindowHandles())
					 {
						driver.switchTo().window(winHandle);
				     }
					 driver.switchTo().frame("searchFrame");
					 logObj.buildDriverElement(Search_Box).sendKeys(Search_UserID);
					 logObj.buildDriverElement(Go_Button).click();
					 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("resultsFrame");
					 Thread.sleep(3000);
					 int table_rows = driver.findElements(By.xpath("//*[@id='User_body']/table/tbody/tr[contains(@class,'dataRow')]")).size();
					 if(table_rows==0)
					 {
						 TestLogger.log("Info", Search_UserID+" is inacitve user hasn't displayed");
					 }
					 else{
					 for(int i=1; i<=table_rows;i++)
					 {
						 String Full_Name = driver.findElement(By.xpath("//*[@id='User_body']/table/tbody/tr[contains(@class,'dataRow')]/th/a")).getText();
						 if(Full_Name.equalsIgnoreCase(Search_UserID))
						 {
							 TestLogger.log("Info", Search_UserID+" is inacitve user and displayed");
						 }
					 }
					 } 
					 driver.close();
					 driver.switchTo().window(parentHandle);
					 logObj.buildDriverElement(Cancel_Button).click();
					 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					 }catch(SeleniumException e){
			    	 e.printStackTrace();
			     }
			    	 
		      }
		   
		   //TC Description: Verify the deleted user in SFDC Account
		      @Test(groups = "FunctionalTest", testName = "CreateAcc_SFDC_07", enabled = true, description = "(ATM) - Verify the deleted UserId in the SFDC LookUp table")
	              public void CreateAcc_SFDC_07() throws Exception
	              {
		    	    try
		    	    {
		    	    this.login();
		    	    String fileName = "ATM.xlsx";
		    	    String sheetName = "Delete_UserId";
		    	    int rowNum =1;
		    	    //String UserID_values =null;
		    	    String Delete_UserId = logObj.getExcelValue(fileName, sheetName, "UserID", rowNum);
		    	    String User_Role = logObj.getExcelValue(fileName, sheetName, "Role", rowNum);
		    	    logObj.buildDriverElement(New_Account_Team_Member).click();
		    	    logObj.buildDriverElement(UserId).sendKeys(Delete_UserId);
		    	    logObj.buildDriverElement(TeamMemberRole).sendKeys(User_Role);
		    	    logObj.buildDriverElement(Save).click();
					Thread.sleep(10000);
					String Navigate_URL_SFDCID=driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[2]/table/tbody/tr[3]/td[2]")).getText();
			        driver.navigate().to("https://gallagher--stage.cs12.my.salesforce.com/"+Navigate_URL_SFDCID);
			        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			        String parentWindow = driver.getCurrentUrl();
			        int no_of_rows = driver.findElements(By.xpath("//*[contains(@id,'00N80000004g7Zg_body')]/table/tbody/tr[contains(@class,'dataRow')]")).size();
			         if(no_of_rows>=5)
			          {
			           logObj.buildDriverElement(ATM_GoToListlink).click();
			           driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		             if(driver.findElement(By.xpath("//th/a[text()='"+Delete_UserId+"']")).isDisplayed())
			           {
			        	   driver.findElement(By.xpath("//th/a[text()='"+Delete_UserId+"']//..//..//td/a[contains(@title,'Delete')]")).click();
			        	   driver.switchTo().alert().accept();
		        		   Thread.sleep(7000);
		        		   TestLogger.log("Info", Delete_UserId+" user deleted successfully");
			           }
			           
			           
			           if(driver.findElements(By.partialLinkText(Delete_UserId)).size() != 0)
			           {
			        	   TestLogger.log("Info", Delete_UserId+" user not deleted ");
			           }
			         }
			         else
			         {
			        	 if(driver.findElement(By.xpath("//*[contains(@id,'00N80000004g7Zg_body')]/table/tbody/tr/th/a[text()='"+Delete_UserId+"']")).isDisplayed())
			        	 {
			        	 driver.findElement(By.xpath("//*[contains(@id,'00N80000004g7Zg_body')]/table/tbody/tr/th/a[text()='"+Delete_UserId +"']//..//..//td/a[contains(@title,'Delete')]")).click();
			        	 driver.switchTo().alert().accept();
		        		 Thread.sleep(5000);
		        		 TestLogger.log("Info", Delete_UserId+" user deleted successfully");
			        	 }
			        	 if(driver.findElements(By.partialLinkText(Delete_UserId)).size() != 0)
				           {
				        	   TestLogger.log("Info", Delete_UserId+" user not deleted ");
				           }
			        	 
			         }
			        driver.get(parentWindow);
		    	    }
		    	    catch(SeleniumException e)
		    	     {
				    	 e.printStackTrace();
				     }
		    	    }
		    //Add Secondry BSD Member with duplicate values and verify validation message	
				@Test(groups = "FunctionalTest", testName = "ATM_BSD_08", enabled = true, description = "(ATM, Duplicate BSD Member for SFDC account ) - Add Secondry BSD Member with duplicate values and verify validation message")
				public void ATM_BSD_08() throws Exception {
					try {
						
						this.login();
						String fileName = "ATM.xlsx";
						String sheetName = "ATM_BSD_08";
						int rowNum = 1;
						logObj.buildDriverElement(New_Account_Team_Member).click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						String Secondary_BSD_Member_name=logObj.getExcelValue(fileName, sheetName, "SecondBSDname", rowNum);
						logObj.buildDriverElement(UserId).sendKeys(Secondary_BSD_Member_name);
						logObj.buildDriverElement(TeamMemberRole).sendKeys("BSD Producer");
						logObj.buildDriverElement(Save).click();
						Thread.sleep(5000);
						logObj.buildDriverElement(userlogout).click();
						Thread.sleep(5000);
						logObj.buildDriverElement(logout).click();
						
						
						
					} catch (SeleniumException e) {
						e.printStackTrace();
					}
					
				}
		
				// TC Description : Verify user is able to create SFDC Account for GBS Member
				@Test(groups = "FunctionalTest", testName = "GBS_CreateAcc_SFDC_01", enabled = true, description = "(ATM, Account Creation for GBC member) - Verify  user able to create new account")
				public void GBS_CreateAcc_SFDC_01() throws Exception {
					try {
						this.login();
						String fileName = "ATM.xlsx";
						String sheetName = "GBS_CreateAcc_SFDC_01";
						int rowNum = 1;
						Thread.sleep(5000);
						driver.navigate().to(GBSUserURL);
						Thread.sleep(5000);
						logObj.buildDriverElement(loginsso1).click();
						Thread.sleep(5000);
						driver.navigate().to(ProfileURL);
						Thread.sleep(5000);
						logObj.buildDriverElement(New1).click();
						WebDriverWait driverWait;
						driverWait = new WebDriverWait(driver,explicitTimeout);
						driverWait.until(ExpectedConditions.elementToBeClickable(logObj.buildLocator(Continue)));
						logObj.isElementExists(logObj.buildDriverElement(Continue), "Continue button exists");				
						logObj.buildDriverElement(Continue).click();
						Thread.sleep(5000);
						//String AccountNametxt=RandomStringUtils.randomAlphabetic(5);
						String GBSAccountName=logObj.getExcelValue("ATM.xlsx", "GBS_CreateAcc_SFDC_01", "GBSAccountName", 1);
				        logObj.buildDriverElement(GBSAccountName).sendKeys(GBSAccountName);
				      	String Country=logObj.getExcelValue("ATM.xlsx", "GBS_CreateAcc_SFDC_01", "Country", 1);
				        Select s2=new Select(logObj.buildDriverElement(GBSMainCountry));
				        s2.selectByVisibleText(Country);
				        logObj.buildDriverElement(GBSMainCountry).sendKeys(Country);
						String Street=logObj.getExcelValue(fileName, sheetName, "Street", rowNum);
				        logObj.buildDriverElement(GBSMainStreet).sendKeys(Street);
				        String city=logObj.getExcelValue(fileName, sheetName, "city", rowNum);
				        logObj.buildDriverElement(GBSMainCity).sendKeys(city);
				        String state=logObj.getExcelValue(fileName, sheetName, "state", rowNum);
				        Select s1=new Select(logObj.buildDriverElement(GBSMainState));
				        s1.selectByVisibleText(state);
				        logObj.buildDriverElement(GBSMainState).sendKeys(state);
				        String zip=logObj.getExcelValue(fileName, sheetName, "zip", rowNum);
				        logObj.buildDriverElement(GBSMainZip).sendKeys(zip);
				        String Phone=logObj.getExcelValue(fileName, sheetName, "phone", rowNum);
				        logObj.buildDriverElement(GBSPhone).sendKeys(Phone);
				       
				        Select Industry=new Select(logObj.buildDriverElement(Industry1));
				        Industry.selectByVisibleText("Agriculture & Mining");
				        
				        Select LeadSource=new Select(logObj.buildDriverElement(GBSLeadSource));
				        LeadSource.selectByVisibleText("Jigsaw");
				        
				        
				        logObj.buildDriverElement(GBSSave).click();
				        Thread.sleep(5000);
				        TestLogger.log("Info", "Account created for GBS ");
						
					} catch (SeleniumException e) {
						e.printStackTrace();
					}
						
				}
				// TC Description : Verify GBS_PrimaryProducer mapped to newly created account
				@Test(groups = "FunctionalTest", testName = "GBS_AccMap_SFDC_01", enabled = true, description = "(ATM, GBS MemberMapping) - Verify GBS_PrimaryProducer added to newly created account")
				public void GBS_AccMap_SFDC_01() throws Exception {
					try {
						
						this.login();
						String fileName = "ATM.xlsx";
						String sheetName = "GBS_AccMap_SFDC_01";
						int rowNum = 1;
						WebElement userid1=logObj.buildDriverElement(GBS_PrimaryProducer);
						String GBS_Account_Name=logObj.getExcelValue(fileName, sheetName, "GBS_Account_Name", rowNum);
						TestLogger.log("Info", "Verifying GBS_PrimaryProducer added to account or not");
						logObj.verifyText(userid1,GBS_Account_Name);
						WebElement userid=logObj.buildDriverElement(GBS_Acc_Team_Member_UserID);
						logObj.verifyText(userid,GBS_Account_Name);
						Thread.sleep(5000);
						String GBSaccurl=driver.getCurrentUrl();
						logObj.buildDriverElement(tab).click();
						Thread.sleep(5000);
						logObj.buildDriverElement(logout1).click();
						Thread.sleep(5000);
						driver.navigate().to(GBSaccurl);
						Thread.sleep(5000);
						WebElement userid2=logObj.buildDriverElement(GBS_SFDC_Team_Member_UserID);
						logObj.verifyText(userid2,GBS_Account_Name);
						Thread.sleep(5000);
						logObj.buildDriverElement(Sharing).click();
                        Thread.sleep(5000);
                        WebElement userdetails=logObj.buildDriverElement(Uservalue_Sharing);
                        logObj.verifyText(userdetails,GBS_Account_Name);
                        
                        
						
					} catch (SeleniumException e) {
						e.printStackTrace();
					}
					
				}
				// TC Description : Verify user is able to create SFDC Account for BSD Member
				@Test(groups = "FunctionalTest", testName = "Merge_3_Accounts_01", enabled = true, description = "(ATM, Creating 3 accounts for merging purpose) - Verify  user able to create multiple accounts")
				public void Merge_3_Accounts_01() throws Exception {
					
					try {
						
						StringBuilder sb=new StringBuilder();
						
						
						for(int i=1;i<=3;i++)
						{
						this.login();
						String fileName = "ATM.xlsx";
						String sheetName = "MergeUsers";
						
						int rowNum = i;
						Thread.sleep(5000);
						driver.navigate().to(BSDUserURL);
						Thread.sleep(5000);
						logObj.buildDriverElement(loginsso).click();
						driver.navigate().to(ProfileURL);
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						logObj.buildDriverElement(New).click();
						WebDriverWait driverWait;
						driverWait = new WebDriverWait(driver,explicitTimeout);
						driverWait.until(ExpectedConditions.elementToBeClickable(logObj.buildLocator(Continue)));
						logObj.isElementExists(logObj.buildDriverElement(Continue), "Continue button exists");				
						logObj.buildDriverElement(Continue).click();
						Thread.sleep(5000);
						//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						//String AccountNametxt=RandomStringUtils.randomAlphabetic(5);
						String AName=logObj.getExcelValue(fileName, sheetName, "AccountName", rowNum);
				        logObj.buildDriverElement(AccountName).sendKeys(AName);
				        Select s=new Select(logObj.buildDriverElement(LeadSource));
				        s.selectByVisibleText("ATE");
				        String Country=logObj.getExcelValue("ATM.xlsx", "MergeUsers", "Country", rowNum);
				        Select s2=new Select(logObj.buildDriverElement(MainCountry));
				        s2.selectByVisibleText(Country);
				        logObj.buildDriverElement(MainCountry).sendKeys(Country);
				        String Street=logObj.getExcelValue(fileName, sheetName, "Street", rowNum);
				        logObj.buildDriverElement(MainStreet).sendKeys(Street);
				        String city=logObj.getExcelValue(fileName, sheetName, "city", rowNum);
				        logObj.buildDriverElement(MainCity).sendKeys(city);
				        String state=logObj.getExcelValue(fileName, sheetName, "state", rowNum);
				        Select s1=new Select(logObj.buildDriverElement(MainState));
				        s1.selectByVisibleText(state);
				        logObj.buildDriverElement(MainState).sendKeys(state);
				        String zip=logObj.getExcelValue(fileName, sheetName, "zip", rowNum);
				        logObj.buildDriverElement(MainZip).sendKeys(zip);
				        String Affi=logObj.getExcelValue(fileName, sheetName, "Affi", rowNum);
				        logObj.buildDriverElement(NicheAffiliations).sendKeys(Affi);
				        Thread.sleep(5000);
				        logObj.buildDriverElement(AddAffiliations).click();
				        Thread.sleep(5000);
				        logObj.buildDriverElement(Save).click();
				        Thread.sleep(5000);
				        sb.append(driver.getCurrentUrl()).append(",");
				        Thread.sleep(5000);
						logObj.buildDriverElement(userlogout).click();
						Thread.sleep(5000);
						logObj.buildDriverElement(logout).click();
						
			    }    
						
						
						logObj.excelLog(1,1,sb.toString());  
						
				
					} catch (SeleniumException e) {
						e.printStackTrace();
					}
					
				}	
				
		// TC Description : Verify user is able to create additional admins for merged accounts
				@Test(groups = "FunctionalTest", testName = "Merge_3_Accounts_02", enabled = true, description = "(ATM, Creating 3 accounts for merging purpose) - Verify user is able to create additional admins for merged accounts")
				public void Merge_3_Accounts_02() throws Exception {
					
					try {
					
						this.login();
						
						//int rowNum = 0;
						Thread.sleep(5000);
						FileInputStream fileInputStream = new FileInputStream("src/test/resources/useraccounturls.xlsx");
						HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
						HSSFSheet worksheet = workbook.getSheet("Sheet0");
						HSSFRow row1 = worksheet.getRow(1);
						HSSFCell cellA1 = row1.getCell((short) 1);
						String usr1URL = cellA1.getStringCellValue();
						//String usr1URL=logObj.getExcelValue1(fileName, sheetName, 1, 1);
						driver.navigate().to(usr1URL);
						Thread.sleep(5000);
			    		
				
					} catch (SeleniumException e) {
						e.printStackTrace();
					}
					
				}
				
			
		}
 