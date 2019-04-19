package com.booking.testing.page;

import com.booking.testing.page.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignInPage extends BasePage {

    private static final String SIGNIN_PAGE_URL = "https://account.booking.com/sign-in/";

    @FindBy(id = "current-account")
    private WebElement currentAccount;

    @FindBy(xpath = "//button[@type='submit']")  //"//button[@type='submit']"
    private WebElement nextButton;

    @FindBy(xpath = "//button[@type='submit']")  //"//button[@type='submit']"
    private WebElement signInButton;

    @FindBy(id = "username")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "username-error")
    private List<WebElement> usernameErrorMessage;

    @FindBy(id = "password-error")
    private List<WebElement> passwordErrorMessage;


    private By passwordFieldLocator = By.id("password");
    private By accountLocator = By.cssSelector("span.header_name.user_firstname.ge-no-yellow-bg");


    public SignInPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }


    public void load() {
        load(SIGNIN_PAGE_URL);
    }


    public String signIn(String username, String password) {
        String errorMsg;
        userNameField.sendKeys(username);
        nextButton.click();

        //Incorrect account
        if (usernameErrorMessage.size() > 0) {
            errorMsg = usernameErrorMessage.get(0).getText();
        }


        //Correct account
        else {
            setExplicitPresenceWait(5, passwordFieldLocator);
            passwordField.sendKeys(password); //The email and password combination you entered doesn't match.
            signInButton.click();

            setImplicitWait(1); //needs to be implicit - cannot set a condition for a specific element

            //Correct account, incorrect password
            if (passwordErrorMessage.size() > 0) {
                errorMsg = passwordErrorMessage.get(0).getText();
            }

            //Correct account and password
            else {
                errorMsg = "";
            }
        }
        return errorMsg;
    }


    public void signedIn() {
        WebElement accountIcon = driver.findElement((accountLocator));
        accountIcon.isDisplayed();
    }

}
