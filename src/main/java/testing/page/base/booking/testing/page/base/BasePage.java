package testing.page.base.booking.testing.page.base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    protected WebDriver driver;


    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public String getPageTitle() {
        return driver.getTitle();
    }


    protected void setExplicitClickableWait(int seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
