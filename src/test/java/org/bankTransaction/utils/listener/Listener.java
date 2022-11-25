package org.bankTransaction.utils.listener;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bankTransaction.reporting.Reporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    /**
     * @param iTestResult
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        Reporter.info("Starting test: " + iTestResult.getName());
    }

    /**
     * @param iTestResult
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Reporter.info("Test: " + iTestResult.getName() + " [PASSED]");
        System.out.println();
    }

    /**
     * @param iTestResult
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Reporter.error("Test: " + iTestResult.getName() + " [FAILED]");
        System.out.println();
    }

    /**
     * @param iTestResult
     */
    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    /**
     * @param iTestResult
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    /**
     * @param iTestContext
     */
    @Override
    public void onStart(ITestContext iTestContext) {

    }

    /**
     * @param iTestContext
     */
    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
