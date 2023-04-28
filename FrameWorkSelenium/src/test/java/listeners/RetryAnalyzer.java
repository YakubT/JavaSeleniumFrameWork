package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int count;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess() && count < 3) {
            count++;
            return true;
        }
        return false;
    }
}
