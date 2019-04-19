package com.booking.testing.page;

import com.booking.testing.page.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {


    @Test(dataProvider = "signInData")
    public void signInTest(String username, String password, String expectedErrorMessage) {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.load();
        String errorMsg = signInPage.signIn(username, password);
        assert errorMsg.equals(expectedErrorMessage);

        //WILL HAVE TO LOOK BETTER - N/A INSTEAD OF EMPTY STRING DOESN'T WORK
        if (errorMsg.equals("")) {
            signInPage.signedIn();
        }
    }

    @DataProvider
    public Object[][] signInData() {
        return new Object[][]{{"dummy_account", "", "Make sure the email address you entered is correct."},
                {"kolorowekretki.koop@gmail.com", "incorrect_password", "The email and password combination you entered doesn't match."},
                {"kolorowekretki.koop@gmail.com", "12345678", ""}};
    }
}
