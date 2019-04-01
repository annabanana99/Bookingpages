package com.booking.testing.page.base.com.booking.testing.page.base;

import testing.driver.booking.testing.driver.DriverFactory;
import testing.entity.WebBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import java.io.File;
import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;


    protected void takeScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = "D:\\Screenshots\\" + "name.png";
        FileUtils.copyFile(scrFile, new File(filePath));
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "environment", "platform"})
    protected void setUp(@Optional("CHROME") WebBrowser browser, @Optional("local") String environment, ITestContext context,
                         @Optional("Windows 10") String platform) throws Exception {
        String testName = context.getCurrentXmlTest().getName();

        DriverFactory factory = new DriverFactory(browser);

        if (environment.equals("SauceLabs")) {
            driver = factory.getSauceDriver(platform, testName);
        } else {
            driver = factory.getDriver();
        }

        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        driver.close();
    }
}
