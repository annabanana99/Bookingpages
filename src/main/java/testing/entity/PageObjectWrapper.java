package testing.entity;

import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PageObjectWrapper {

    private List<WebElement> webElements;

    public PageObjectWrapper(WebElement... webElements) {
        this.webElements = Arrays.asList(webElements);
    }

    public PageObjectWrapper() {
    }

    // add, remove, find etc...
    public void addWebElement(WebElement webElement) {
        webElements.add(webElement);
    }

    // just example, don't know if that makes sense ^_^
    public Optional<WebElement> getWebElementByTagName(String tagName) {
        return webElements.stream()
                .filter(w -> w.getTagName().equals(tagName))
                .findFirst();
    }
}

