package QKART_TESTNG;


import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {
    public void onTestStart(ITestResult result) {
        QKART_Tests.takeScreenshot(QKART_Tests.driver, "Test Start", result.getName());
       
    }

    public void onTestSuccess(ITestResult result) {

       
        QKART_Tests.takeScreenshot(QKART_Tests.driver, "Test Success", result.getName());
        
    }

    public void onTestFailure(ITestResult result) {

        QKART_Tests.takeScreenshot(QKART_Tests.driver, "Test Failure", result.getName());
       
            
    }

}



