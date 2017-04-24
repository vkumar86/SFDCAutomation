package flow;

import org.testng.annotations.Factory;
import library.InitialSetUp;
import test.ATMScenarios.SFDCaccountcreation;


import java.util.ArrayList;

/* TestNG factory class where we list/add the class to be executed, this factory class is called in testng.xml */ 
 
public class TestFactory extends InitialSetUp {
			
	public TestFactory() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Factory
	public Object[] PWDModuleInitial() {

		ArrayList<Object> modules = new ArrayList<Object>();
		
		try {
			
		   //modules.add(new LoginTC());    // Only login test case is used in the class, Since we needed the application to login once
		   modules.add(new SFDCaccountcreation());
		   
 		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modules.toArray();
	}
}