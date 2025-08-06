package page.DSP;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
    Page page;
    private String url = "https://q-line-iis.alqemam.com/qDSP_Test_v14.3.01";

    private final Locator welcomeMsgLABELLocator;
    private final Locator userICONLocator;
    private final Locator userNameLABELLocator;
    private final Locator userMailLABELLocator;


    public HomePage(Page page) {
        this.page = page;
        welcomeMsgLABELLocator = page.getByText("مرحبًا بك في منصة القمم للخدمات الرقمية");
        userICONLocator = page.locator("span[class='u-icon u-reset-icon u-arrow-down-icon']");
        userNameLABELLocator = page.locator(".u-fs-xs.u-fw-500.u-text-neutral-900.l-header-dropdown__text");
        userMailLABELLocator = page.locator(".u-fs-xs.u-text-neutral-500.l-header-dropdown__text");

    }

    public void navigateToHomePage() {
        page.navigate(url);
    }

    public boolean isHomePageDisplayed() {
        return welcomeMsgLABELLocator.isDisabled();
    }
    public String[] getUserInfo() {
        userICONLocator.click();
        return new String[]{userNameLABELLocator.textContent(),userMailLABELLocator.textContent()};
    }

}
