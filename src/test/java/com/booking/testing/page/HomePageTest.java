package com.booking.testing.page;

import com.booking.testing.page.base.BaseTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class HomePageTest extends BaseTest {

    

    @Test(enabled = false)
    @Parameters("environment")
    public void logoDisplayedTest(@Optional("local") String environment) throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        if (environment.equals("SauceLabs")) { //I want a separate screenshot for every browser
            takeScreenshot();
        }

    }

    @Test(enabled = false)
    public void verifyPageTitleTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        assert homePage.getPageTitle().contains("Booking.com");
    }


    @Test(enabled = false)
    public void imagesDisplayedTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        List<String> promoImgsUrls = homePage.nonDisplayedPromoImgs();
        assert promoImgsUrls.size() == 0;
    }


    @Test(enabled = false)
    public void setCurrencyTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.setEuroCurrency();
    }


    @Test(enabled = false)
    public void setEnglishLanguageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.setEnglishLanguage();
    }


    @Test(enabled = false)
    public void checkAvailableLanguagesTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        assert homePage.languagesCount() == 43;
        System.out.println(homePage.languagesCount() );
    }


    @Test(enabled = false)
    public void logoDisplayTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.logoDisplayed();
    }


    @Test(enabled = false)
    public void menuButtonsTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.menuBtnsEnabled();
    }

    @Test(enabled=false)
    public void tabsTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.checkAccommodationTab();
        homePage.checkFlightsTab();
        homePage.checkCarRentalsTab();
        homePage.checkAirportTaxisTab();
    }


    @Test()
    public void datesSelectionTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.randomDateCheckInOut(2,7);
    }

    //dates here won't work, not clickable
    @Test(enabled=false)
    public void searchAccommodationE2ETest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.searchAccommodationE2E("New Yor", 1, 4);
    }

    //correct this
    @Test(enabled=false)
    public void stepperControlsTest() {
        HomePage homePage = new HomePage(driver);
        homePage.load();
        homePage.clickControls(2,4,3);
    }


}