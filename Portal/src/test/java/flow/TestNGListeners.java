package flow;

import java.lang.reflect.*;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.internal.annotations.*;
import org.testng.xml.XmlSuite;
import java.util.List;

/* This class contains all the abstract classes of the TestNGlistners used and the implementation can 
be found in ImplementListeners.java */

public interface TestNGListeners {

	/* Abstract of the listeners, which will change the "enabled" property used in TestNG method, 
	will read values from "flowConfig.properties" and implementation is in ImplementListeners.java */
	
	public interface IAnnotationTransformer {
		public void transform(ITest annotation, Class testClass, Constructor testConstructor, Method testMethod);
	}

	/* Abstract of the listeners, which will execute test methods inside class in order which the methods are written, 
	and also read values from TestFactory.java to get class in order, implementation is in ImplementListeners.java */
	
	public interface IMethodInterceptor {
		public default List<IMethodInstance> intercept(List<IMethodInstance> arg0, ITestContext arg1) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}