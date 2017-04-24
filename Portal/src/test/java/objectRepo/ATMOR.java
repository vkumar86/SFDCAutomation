package objectRepo;

import org.openqa.selenium.By;

import library.LogInFunc;

public interface ATMOR {
	
// Common object for ATM page (Eg : links/sub links/Add/Save)	
	
	public static String ProfileURL="https://gallagher--stage.cs12.my.salesforce.com/001/o";
	public static String loginsso="name"+"login";
	
//	create_Account_PrimaryProducer Objects
	public static String BSDUserURL="https://gallagher--stage.cs12.my.salesforce.com/005V0000003J9Uu?noredirect=1&isUserEntityOverride=1";
	public static String userlogout="xpath"+"//*[@id='globalHeaderNameMink']";
	public static String logout="xpath"+"//*[@id='globalHeaderBar']/div[2]/div/div/div/ul/li[7]/a";
	public static String New="name"+"new";
	public static String Continue="xpath"+"//input[@value='Continue']";
	public static String AccountName="xpath"+"//input[@id='acc2']";
	public static String LeadSource="id"+"00N80000004g8uu";
	public static String Lead_Source_Sub_Type="id"+"00N80000004g8uv";
	public static String MainCountry="id"+"acc17country";
	public static String MainStreet="id"+"acc17street";
	public static String MainCity="id"+"acc17city";
	public static String MainState="id"+"acc17state";
	public static String MainZip="id"+"acc17zip";
	public static String NicheAffiliations="id"+"00N80000004g8uy_unselected";
	public static String AddAffiliations="xpath"+"//*[@id='00N80000004g8uy_right_arrow']";
	public static String Save="name"+"save";
	
//	Objects to Verify BSD PrimayProducer Added for new account
	public static String BSD_PrimaryProducer="xpath"+"//div[@id='CF00N80000004g8ui_ileinner']/a[contains(text(),'Ramya')]";
	public static String Acc_Team_Members="xpath"+"//*[contains(@id,'00N80000004g7Zg_link')]";
	public static String Acc_Team_Member_UserID="xpath"+"//*[contains(@id,'00N80000004g7Zg_body')]/table/tbody/tr[2]/th/a";
	public static String ATM_GoToListlink="xpath"+"//*[contains(@id,'00N80000004g7Zg_body')]/div[@class='pShowMore']/a[2]"; //Mani
	
//Create Secondary BSD member
		public static String New_Account_Team_Member="name"+"new00N80000004g7Zg";
        public static String UserId="id"+"CF00N80000004g7aF";
        public static String TeamMemberRole="id"+"00N80000004g7aA";
        public static String SaveBSD="name"+"save";
        public static String SFDCID="xpath"+"//*[@id='ep']/div[2]/div[2]/table/tbody/tr[3]/td[2]";
        public static String Second_BSD_Member="xpath"+"//*[contains(@id,'00N80000004g7Zg_body')]/table/tbody/tr[3]/th/a";
        public static String SaveAndNewBSD="name"+"save_new"; //Mani
        public static String LooKUp_Icon_image = "class"+"lookupIcon"; //Mani
        public static String LooKUp_Icon = "id"+"CF00N80000004g7aF_lkwgt";//Mani
        public static String Cancel_Button = "name"+"cancel";//Mani
//Duplicate BSD member Validation
        public static String Duplicate_Account_Team_Member="name"+"new00N80000004g7Zg";
        
     // Objects related to sharing	- Mani
    	public static String Sharing1="name"+"share";
    	
    //Objects to verify SFDC Account Team table -Mani
    	public static String SFDC_GoTOLink = "xpath"+"//*[contains(@id,'RelatedAccountSalesTeam_body')]/div[@class='pShowMore']/a[2]";
    	
    //Objects in LookUp window   --Mani
        public static String Search_Box = "id"+"lksrch";
        public static String Go_Button = "name"+"go";
	
        
        
//create_Account_GBS Objects(GBS_CreateAcc_SFDC_01)
        public static String GBSUserURL="https://gallagher--stage.cs12.my.salesforce.com/005C0000009jtNQ?noredirect=1&isUserEntityOverride=1";
        public static String Home="xpath"+"//li[@id='home_Tab']/a[contains(text(),'Home')]";
        public static String loginsso1="xpath"+"//*[@id='topButtonRow']/input[4]";
        String New1="xpath"+"//*[@id='hotlist']/table/tbody/tr/td[2]/input";
        public static String GBSAccountName="xpath"+"//input[@id='acc2']";
        public static String GBSMainCountry="id"+"acc17country";
    	public static String GBSMainStreet="id"+"acc17street";
    	public static String GBSMainCity="id"+"acc17city";
    	public static String GBSMainState="id"+"acc17state";
    	public static String GBSMainZip="id"+"acc17zip";
    	public static String GBSPhone="xpath"+"//*[@id='acc10']";
    	public static String Industry1="xpath"+"//*[@id='acc7']";
    	
    	public static String GBSLeadSource="xpath"+"//*[@id='00N800000034RQh']";
    	
    	
    	public static String GBSSave="name"+"save";
    	
       //GBS_PrimaryProducer mapped to newly created account objects
    	public static String GBS_PrimaryProducer="xpath"+"//div[@id='CF00N80000004g7Y4_ileinner']/a[contains(text(),'P.J. Anast')]";
    	
    	//public static String GBS_Acc_Team_Members="xpath"+"//*[contains(@id,'00N80000004g7Zg_link')]";
    	public static String GBS_Acc_Team_Member_UserID="xpath"+"//*[contains(@id,'00N80000004g7Zg_body')]/table/tbody/tr[2]/th/a";
    	public static String tab="xpath"+"//*[@id='globalHeaderNameMink']";
    	public static String logout1="xpath"+"//a[contains(text(),'Logout')]";
    	public static String Sharing="xpath"+"//*[@id='topButtonRow']/input[@name='share']";
    	public static String Uservalue_Sharing="xpath"+"//*[@id='bodyCell']/div[5]/div/div[2]/table/tbody/tr[3]/td[2]/a";   	
    	public static String GBS_SFDC_Team_Member_UserID="xpath"+"//*[contains(@id,'RelatedAccountSalesTeam_body')]//table/tbody/tr[2]/th/a";
    	
}