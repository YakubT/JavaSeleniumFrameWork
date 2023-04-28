package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getName()+" Finished successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName()+" Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getName()+" skipped");
    }

}
