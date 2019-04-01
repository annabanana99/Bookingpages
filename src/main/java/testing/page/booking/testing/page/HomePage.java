package testing.page.booking.testing.page;

import com.booking.testing.page.base.booking.testing.page.base.BasePage;
import org.apache.commons.logging.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(Log.class);

    private static final String HOME_PAGE_URL = "https://www.booking.com/";
    private List<String> imgUrls = new ArrayList<>();

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

    @FindBy(xpath = "//*[@id='cross-product-bar']//a[contains(@data-et-click,'taxi')]")
    private WebElement airportTaxisTab;

    @FindBy(xpath = "//*[@id='cross-product-bar']//a[contains(@data-et-click,'accommodation')]")
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

    @FindBy(xpath = "//div[@class='bui-stepper__wrapper']/*[position()>1]")
    private List<WebElement> controls;

    @FindBy(css = "button.sb-searchbox__button")
    private WebElement searchButton;

    @FindBy(xpath = "//tr[@class='bui-calendar__row'][position()>1]//td[contains(@class,'bui-calendar__date')]")
    private List<WebElement> calendarDays;

    //Promo images
    @FindBy(xpath = "*//div[@class='promote-articles__wrapper']//img")
    private List<WebElement> promoImages;


    public HomePage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }


    public void load() {
        driver.get(HOME_PAGE_URL);
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


    private void enterDestination(String destination) {
        destinationField.sendKeys(destination);
    }

    private List<WebElement> getCheckInOutDates() {
        //verify enabled days, leave out the valid ones

        for (WebElement day : calendarDays) {
            if (day.getAttribute("class").contains("disabled") || (day.getAttribute("class").contains("empty"))) {
                calendarDays.remove(day);
            }
        }
        return calendarDays;
    }


    //TEST THIS
    public void randomDateCheckInOut() {
        WebElement checkIn = getCheckInOutDates().get(0);
        WebElement checkOut = getCheckInOutDates().get(2);
        String checkin = checkIn.getText();
        String checkout = checkOut.getText();
        System.out.println("Check in: " + checkin + ". Checkout: " + checkout);
    }


    public void searchAccommodation(String destination) {
        enterDestination(destination);
        destinationField.click();
        suggestedDestination.click();
        randomDateCheckInOut();
        workCheckbox.click();
        searchButton.click();

    }


/*    private void iterateControls() {
        HashMap<String, WebElement> rooms = new HashMap<>();
        HashMap<String, WebElement> adults = new HashMap<>();
        HashMap<String, WebElement> children = new HashMap<>();

        List<String> names = Arrays.asList("substract","count","add");
        while (WebElement element : controls){
            for (String name : names) {
                rooms.put(name, element);
            }

            for (WebElement element : controls) {
                for (String name : names) {
                    rooms.put(name, element); }
            }
        }
    }*/


    protected void checkTabLinks() {
        flightsTab.isSelected();
        accommodationsTab.isSelected();
        carsRentalTab.isSelected();
        airportTaxisTab.isSelected();
    }


    public String nonDisplayedPromoImgs() {
        boolean allPromoImgDisplayed;
        for (WebElement img : promoImages) {
            allPromoImgDisplayed = img.isDisplayed();
            if (!allPromoImgDisplayed) {
                String imgUrl = img.getAttribute("style");
                imgUrl = imgUrl.substring(22);
                imgUrls.add(imgUrl);
            }
        }
        return imgUrls.toString();
    }


    public int nonDisplayedPromoImgsSize() {
        return imgUrls.size();
    }


    public void menuBtnsEnabled() {
        setExplicitClickableWait(5, registerButton);
        setExplicitClickableWait(5, signInButton);
        setExplicitClickableWait(5, listProptyButton);
        registerButton.isEnabled();
        signInButton.isEnabled();
        listProptyButton.isEnabled();
    }


    //TEST THIS
    protected void logoDisplayed() {
        logoImg.isDisplayed();
    }
}




