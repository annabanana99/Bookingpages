package com.booking.testing.listener;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.trace("***********Test started************");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.trace("***********Test successful************");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.trace("***********Test failed************");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.trace("***********Test skipped************");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.trace("//"); // TODO: put in some text here
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.trace("***********START************");

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.trace("***********FINISHED************");
    }
}


