package com.booking.testing.page;

import com.booking.testing.page.base.BasePage;
import org.apache.commons.logging.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(Log.class);

    private static final String HOME_PAGE_URL = "https://www.booking.com/";
    private List<String> imgUrls = new ArrayList<>();
    private HashMap<String, WebElement> mapControls;
    private List<WebElement> validDays;


    //Logo
    @FindBy(id = "logo_no_globe_new_logo")
    private WebElement logoImg;

    //Top buttons
    @FindBy(id = "current_account_create")
    private WebElement registerButton;

    @FindBy(id = "current_account")
    private WebElement signInButton;

    @FindBy(id = "add_property_topbar")
    private WebElement listProptyButton;


    //Tab links
    @FindBy(xpath = "//*[@id='cross-product-bar']//a[contains(@data-et-click,'flights')]")
    private WebElement flightsTab;

    @FindBy(xpath = "//*[@id='cross-product-bar']//a[contains(@data-et-click,'car')]")
    private WebElement carsRentalTab;

    @FindBy(xpath = "//*[@id='cross-product-bar']//a[contains(@data-et-click,'rideways')]")
    private WebElement airportTaxisTab;

    @FindBy(xpath = "//*[@id='cross-product-bar']//span[contains(@data-et-click,'accommodation')]")
    private WebElement accommodationsTab;

    //Languages
    @FindBy(xpath = "//div[@id='user_form']//li[@data-id='language_selector']")
    private WebElement languageSelector;

    @FindBy(xpath = "//li//a[@class = 'popover_trigger']")
    private WebElement currentLanguageInfo;

    @FindBy(xpath = "//*[@id='current_language_foldout']//a[@hreflang='en-us']")
    private WebElement languageEnglishUsSelector;

    @FindBy(xpath = "//div[@id='current_language_foldout']/div[2]//ul/li/a")
    private List<WebElement> languagesList;

    //Currency
    @FindBy(xpath = "//div[@id='user_form']//li[@data-id='currency_selector']")
    private WebElement currencySelector;

    @FindBy(xpath = "//a[@data-currency='EUR']")
    private WebElement eurCurrency;

    //Destination & other relevant fields
    @FindBy(id = "ss")
    private WebElement destinationField;

    @FindBy(xpath = "//ul[contains(@class,'c-autocomplete__list')]/li[contains(@class, c-autocomplete__item)][1]")
    private WebElement suggestedDestination;

    @FindBy(id = "sb_travel_purpose_checkbox")
    private WebElement workCheckbox;

    @FindBy(id = "xp__guests__toggle")
    private WebElement guestsInputUnload;

    @FindBy(xpath = "//div[@class='bui-stepper__wrapper']")
    private List<WebElement> stepperControlsRooms;

    @FindBy(css = "button.sb-searchbox__button")
    private WebElement searchButton;

    @FindBy(css = "span.sb-date-field__icon.sb-date-field__icon-btn.bk-svg-wrapper.calendar-restructure-sb")
    private WebElement datesUnload;

    @FindBy(xpath = "//tr[@class='bui-calendar__row'][position()>1]//td[not(contains(@class,'disabled')) and not(contains(@class,'empty'))]")
    private List<WebElement> validDates;


    //Promo images
    @FindBy(xpath = "*//div[@class='promote-articles__wrapper']//img")
    private List<WebElement> promoImages;




    public HomePage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }


    public void load() {
        load(HOME_PAGE_URL);
    }


    public HomePage setEnglishLanguage() {
        String currentLanguageinfo = currentLanguageInfo.getAttribute("aria-label");
        if (!currentLanguageinfo.contains("English")) {
            languageSelector.click();
            languageEnglishUsSelector.click();
        }
        return this;
    }


    public int languagesCount() {
        return languagesList.size();
    }


    public void setEuroCurrency() {
        currencySelector.click();
        setExplicitClickableWait(15, eurCurrency);
        eurCurrency.click();
    }


    private void enterSelectDestination(String destination) {
        destinationField.sendKeys(destination);
        setExplicitClickableWait(5,suggestedDestination);
        suggestedDestination.click();
    }



    public void randomDateCheckInOut(int startIndex, int endIndex) {
        //index 0 starts with today
        datesUnload.click();
        WebElement checkIn = validDates.get(startIndex);
        setExplicitClickableWait(3,checkIn);
        checkIn.click();
        WebElement checkOut = validDates.get(endIndex);
        checkOut.click();
        String checkin = checkIn.getAttribute("data-date");
        String checkout = checkOut.getAttribute("data-date");
        logger.info("Check in: " + checkin + ". Checkout: " + checkout);
    }


    //NIE DZIALA, NULL POINTER EXCEPTION
    private HashMap<String, WebElement> getStepperElementsMap(String group) {
        String controlsMainXpath =  "//div[contains(@class,'" + group +"')]//div[@class='bui-stepper__wrapper']//*[position()>1]";
        List<WebElement> controlsList = driver.findElements(By.xpath(controlsMainXpath));
        System.out.println(controlsList);

       mapControls.put("substract",controlsList.get(0));
       mapControls.put("currentValue",controlsList.get(1));
       mapControls.put("add",controlsList.get(1));

       return mapControls;
    }



    //NIE DZIALA, NULL POINTER EXCEPTION - getStepperElementsMap
    public void clickControls(int roomsCount, int adultsCount, int childrenCount) {
        //the initial numbers are: 2 adults, 1 room, 0 children

        guestsInputUnload.click();
        HashMap<String, WebElement> roomControls = getStepperElementsMap("rooms");
        HashMap<String, WebElement> adultsControls = getStepperElementsMap("adults");
        HashMap<String, WebElement> childrenControls = getStepperElementsMap("children");

        int newRoomCount = 0;
        int newAdultsCount = 0;
        int newChildrenCount = 0;

        while (roomsCount - 1 > 0) {
            roomControls.get("add").click();
            roomsCount++;
            newRoomCount++;
        }

        while (adultsCount - 2 > 0) {
            adultsControls.get("add").click();
            adultsCount++;
            newAdultsCount++;
        }

        while (childrenCount > 0) {
            childrenControls.get("add").click();
            childrenCount++;
            newChildrenCount++;
        }
        int counts[] = {newRoomCount,newAdultsCount, newChildrenCount};
    }


    //dates here won't work, not clickable
    public void searchAccommodationE2E(String destination, int startIndex, int endIndex) {
        enterSelectDestination(destination);
        datesUnload.click();
       // setImplicitWait(3);
        randomDateCheckInOut(startIndex, endIndex);
/*        guestsInputUnload.click();
        HashMap<String, WebElement> roomControls = getStepperElementsMap("rooms");
        String currentRoomCount = roomControls.get("currentValue").getText()

        WebElement roomAddition = roomControls.get("add");
        HashMap<String, WebElement> adultsControls = getStepperElementsMap("adults");
        String currentAdultsCount = roomControls.get("currentValue").getText();
        WebElement adultsAdd= roomControls.get("add");
        WebElement adultsSubstract= roomControls.get("substract");
        String currentChildrenCount = roomControls.get("currentValue").getText();
        WebElement childrenAdd= roomControls.get("add");
        WebElement childrenSubstract= roomControls.get("substract");

        Actions actions = new Actions(driver);
        actions.click(adultsAdd).click(adultsAdd).click(childrenAdd).click(childrenAdd).click(roomAddition).perform();*/

       // workCheckbox.click(); //correct this
        searchButton.click();


    }


    protected void checkAccommodationTab() {
        if(!accommodationsTab.isSelected()) {
            accommodationsTab.click();
        }
    }

    protected void checkCarRentalsTab() {
        if(!carsRentalTab.isSelected()) {
            carsRentalTab.click();
        }
    }

    protected void checkFlightsTab() {
        if(!flightsTab.isSelected()) {
            flightsTab.click();
        }
    }

    protected void checkAirportTaxisTab() {
        if(!airportTaxisTab.isSelected()) {
            airportTaxisTab.click();
        }
    }



    public List<String> nonDisplayedPromoImgs() {
        boolean allPromoImgDisplayed;
        for (WebElement img : promoImages) {
            allPromoImgDisplayed = img.isDisplayed();
            if (!allPromoImgDisplayed) {
                String imgUrl = img.getAttribute("style");
                imgUrl = imgUrl.substring(22);
                imgUrls.add(imgUrl);
            }
        }
        if(imgUrls.size() != 0) {
            logger.info("Non-displayed promo images: " + imgUrls.toString()); }
        return imgUrls;
    }




    public void menuBtnsEnabled() {
        setExplicitClickableWait(5, registerButton);
        setExplicitClickableWait(5, signInButton);
        setExplicitClickableWait(5, listProptyButton);
        registerButton.isEnabled();
        signInButton.isEnabled();
        listProptyButton.isEnabled();
    }



    protected void logoDisplayed() {
        logoImg.isDisplayed();
    }
}




