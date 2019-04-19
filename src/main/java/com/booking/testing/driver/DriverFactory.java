package com.booking.testing.driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.booking.testing.entity.WebBrowser;
import java.net.MalformedURLException;
import java.net.URL;


public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private WebBrowser webBrowser;

    private static final String TEST_PLATFORM = "platform";
    private static final String TEST_BROWSER_NAME = "browserName";
    private static final String TEST_NAME = "name";

    public DriverFactory(WebBrowser webBrowser) {
        this.webBrowser = webBrowser;
    }

    public WebDriver getDriver() throws Exception {
        switch (webBrowser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", ClassLoader.getSystemResource("drivers/chromedriver.exe").toURI().getPath());
                driver.set(new ChromeDriver());
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", ClassLoader.getSystemResource("drivers/geckodriver_old.exe").toURI().getPath());
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                driver.set(new FirefoxDriver(options));
                break;
            case EDGE:
                System.setProperty("webdriver.edge.driver", ClassLoader.getSystemResource("drivers/MicrosoftWebDriver.exe").toURI().getPath());
                driver.set(new EdgeDriver());
                break;
            case PHANTOM_JS:
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setJavascriptEnabled(true);
                caps.setCapability("takesScreenshot", true);
                caps.setCapability(
                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        ClassLoader.getSystemResource("drivers/phantomjs.exe").toURI().getPath()
                );
                driver.set(new PhantomJSDriver(caps));
            default:
                throw new IllegalStateException("WebBrowser + " + webBrowser + " is not supported!");
        }
        return driver.get();
    }


    public WebDriver getSauceDriver(String platform, String name) {
        //https://wiki.saucelabs.com/display/DOCS/Java+Test+Setup+Example
        String user = "booking_project";
        String key = "f52cc90b-6273-4935-a916-c7eb9b0d31f4";
        String sauceUrl = "http://" + user + ":" + key + "@ondemand.eu-central-1.saucelabs.com/wd/hub;"; //"@ondemand.saucelabs:80/wd/hub";

        DesiredCapabilities desCaps = new DesiredCapabilities();
        desCaps.setCapability(TEST_BROWSER_NAME, "browser");

        if (platform != null) {
            desCaps.setCapability(TEST_PLATFORM, platform);
        } else {
            desCaps.setCapability(TEST_PLATFORM, "Windows 10");
        }

        //setting test name
        desCaps.setCapability(TEST_NAME, name);

        try {
            driver.set(new RemoteWebDriver(new URL(sauceUrl), desCaps));
        } catch (MalformedURLException e) {
            logger.error("Exception during Sauce Driver initialization, ", e);
        }
        return driver.get();
    }
}
