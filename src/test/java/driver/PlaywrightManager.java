package driver;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.regex.Pattern;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywrightManager {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    /**
     * This method initializes Playwright, launches a browser, navigates to a login page,
     * performs login actions, and verifies the page title after login.
     * It also takes a screenshot of the page after login.
     */

    public PlaywrightManager(){
        playwright = Playwright.create();
        BrowserType.LaunchOptions browserType = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setExecutablePath(Paths.get("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe"));
        browser = playwright.chromium().launch(browserType);
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }
    public Page getPage() {
        return page;
    }
    public Browser getBrowser() {
        return browser;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }
    @Test
public void test() {
    Playwright playwright = Playwright.create();
    BrowserType.LaunchOptions browserType = new BrowserType.LaunchOptions().setHeadless(false)
            .setSlowMo(100).setTimeout(10000)
            .setExecutablePath(Paths.get("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe"));
//        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    Browser browser = playwright.chromium().launch(browserType);
    Page page = browser.newPage();
    page.navigate("https://q-line-iis.alqemam.com/qcms_test_v14.3.12/login.aspx");
    // Expect a title "to contain" a substring.
    assertThat(page).hasTitle("تسجيل الدخول");
    // Create a locator for the username input.
//        Locator userIdLocator = page.locator("input[id='txtUserName']");
    Locator userIdLocator = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("الرقم الوظيفي"));
    // Fill the input with a username.
    userIdLocator.fill("0560560");
    // Create a locator for the password input.
    Locator passwordLocator = page.locator("input[id='txtUserPass']");
    // Fill the input with a password.
    passwordLocator.fill("24602460");
    // Click the login button.
    Locator loginButtonLocator = page.getByRole(AriaRole.BUTTON,
            new Page.GetByRoleOptions().setName("تسجيل الدخول"));
    // Click the login button.
    loginButtonLocator.click();
    // Wait for the page to load after login
    assertThat(page).hasTitle("الصفحة الرئيسية");
    // Take a screenshot.
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("resources/screenshot/example.png")));
    }
}

