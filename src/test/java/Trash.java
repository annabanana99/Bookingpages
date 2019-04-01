import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;

// content might be used later

public class Trash {

    @FindBy(xpath = "//div[@class='bui-calendar__content']/div[@class='bui-calendar__wrapper'][1]/div[@class = 'bui-calendar__month']")
    private List<WebElement> firstMonthDates;

    @FindBy(xpath = "//div[@class='bui-calendar__content']/div[@class='bui-calendar__wrapper'][2]/div[@class = 'bui-calendar__month']")
    private List<WebElement> secondMonthDates;

    @FindBy(xpath = "//div[@class='bui-calendar__content']/div[@class='bui-calendar__wrapper'/div[@class = 'bui-calendar__month']")
    private List<WebElement> wholeMonthsTables;


    public SoftAssert softAssert() {
        SoftAssert softAssert = new SoftAssert();
        return softAssert;
    }


}





