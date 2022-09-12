package AtechCompany.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AtechCompany.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{
	   ExtentTest test;
	   ExtentReports extent = ExtentReportNG.getReportObject(); 
	   
	   ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread Safe
	   
	   public void onTestStart(ITestResult result) {
		   test = extent.createTest(result.getMethod().getMethodName());
		   extentTest.set(test); // assign unique thread id(test method id) -> test
	   }
	   
	   public void onTestSuccess(ITestResult result) {
		   extentTest.get().log(Status.PASS, "Test Passed");
	   }
	   
	   public void onTestFailure(ITestResult result) {
		   //test.log(Status.PASS, "Test Failed");
		   extentTest.get().fail(result.getThrowable());
		   try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
						   .get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			             
		   
		   
		   //Screenshot and then report
		   String filePath = null;
		   
		   try {
			   filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		   }
		   catch(IOException e){
			   e.printStackTrace();
		   }
		   extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	   }
	   
	   public void onFinish(ITestContext context) {
		   extent.flush();
	   }
}
