package com.booking.testing.page.homepage.com.booking.testing.page.homepage;

import com.booking.testing.driver.booking.testing.driver.DriverFactory;
import com.booking.testing.entity.WebBrowser;
import com.booking.testing.page.base.com.booking.testing.page.base.BaseTest;
import com.booking.testing.page.booking.testing.page.HomePage;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;


public class HomePageTest extends BaseTest {


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



    @Test
    public void check()  {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.searchAccommodation("New Yor");
    }




    @Test
    @Parameters("environment")
    public void logoDisplayed(@Optional("local") String environment) throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        if (environment.equals("SauceLabs")) { //I want a separate screenshot for every browser
            takeScreenshot();
        }

    }

    @Test
    public void verifyPageTitle() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        assert homePage.getPageTitle().contains("Booking.com");
    }


    @Test
    public void imagesDisplayed() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.nonDisplayedPromoImgs();
        if (homePage.nonDisplayedPromoImgsSize() > 0) {
            homePage.nonDisplayedPromoImgs();
        }
    }


    @Test
    public void setCurrency() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.setEuroCurrency();
    }


    @Test
    public void setEnglishLanguage() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.setEnglishLanguage();
    }


    @Test
    public void checkAvailableLanguages() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        assert homePage.languagesCount() == 43;
        System.out.println(homePage.languagesCount() );
    }



}
