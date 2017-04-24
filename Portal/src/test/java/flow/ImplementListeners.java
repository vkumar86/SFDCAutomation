package flow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import java.util.*;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;


import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/* This class contains all the implementation of TestNGlistners which is in TestNGListeners.java */

public class ImplementListeners implements IAnnotationTransformer, IMethodInterceptor {
	Properties props = new Properties();

	/* 
	 * Description : Method which will change the "enabled" property used in TestNG method
	 * Parameters  :
	 * annotation - The annotation that was read from your test class
	 * testClass - If the annotation was found on a class, this parameter represents this class (null otherwise)
	 * testConstructor - If the annotation was found on a constructor, this parameter represents this constructor (null otherwise)
	 * testMethod - If the annotation was found on a method, this parameter represents this method (null otherwise)
	 */
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		try {
			String propFilename = "flowConfig.properties";
			InputStream inputstream = getClass().getClassLoader().getResourceAsStream(propFilename);
			props.load(inputstream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (props.getProperty(annotation.getTestName()).trim().contains("true")) {
			annotation.setEnabled(true);
		} else {
			annotation.setEnabled(false);
		}
	}
	

	/* 
	 * Description : Method which will execute test methods inside class in order which the methods are written
	 * Note : Only methods that have no dependents and that don't depend on any other test methods will be passed in parameter.
	 * Parameters  :
	 * ITestContext - This is passed in the intercept method so that implementers can set user values
	 * methods - List of test methods which are to be executed
	 * IMethodInstance - Return value - Represents the list of test methods
	 */
	
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		Comparator<IMethodInstance> comparator = new Comparator<IMethodInstance>() {

			private int getLineNo(IMethodInstance mi) {
				int result = 0;
				String methodName = mi.getMethod().getConstructorOrMethod().getMethod().getName();
				String className = mi.getMethod().getConstructorOrMethod().getDeclaringClass().getCanonicalName();
				ClassPool pool = ClassPool.getDefault();
				try {
					CtClass cc = pool.get(className);
					CtMethod ctMethod = cc.getDeclaredMethod(methodName);
					result = ctMethod.getMethodInfo().getLineNumber(0);
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
				return result;
			}

			public int compare(IMethodInstance m1, IMethodInstance m2) {
				return getLineNo(m1) - getLineNo(m2);
			}
		};

		IMethodInstance[] array = methods.toArray(new IMethodInstance[methods.size()]);
		IMethodInstance[] newarray = methods.toArray(new IMethodInstance[methods.size()]);
		Arrays.sort(array, comparator);

		try {
			TestFactory testFactory = new TestFactory();
			Object[] modules = testFactory.PWDModuleInitial();
			int z = 0;
			for (int i = 0; i < modules.length; i++) {
				for (int k = 0; k < methods.size(); k++) {
					if (Arrays.asList(array).get(k).toString()
							.contains(Arrays.asList(modules).get(i).getClass().getSimpleName())) {
						Arrays.asList(newarray).set(z, Arrays.asList(array).get(k));
						z++;
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return Arrays.asList(newarray);
	}
}