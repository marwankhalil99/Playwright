package utils;

import com.microsoft.playwright.Locator;

public class ElementActions {
    // This class can contain utility methods for interacting with elements
    // such as click, fill, getText, etc. using Playwright's API.

    // Example method to click an element
    public static void clickElement(Locator locator) {
        locator.click();
    }

    // Example method to fill an input field
    public static void fillInput(Locator locator, String text) {
        locator.fill(text);
    }

    // Example method to get text from an element
    public static String getText(Locator locator) {
        return locator.textContent();
    }
}
