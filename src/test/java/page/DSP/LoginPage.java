package page.DSP;

import apis.APIs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static page.DSP.CreateNewUserPage.*;

public class LoginPage {
    Page page;
    APIs apIs;
    private final Locator createNewUserLocator;
    private final Locator nationalIdTXTLocator;
    private final Locator passwordTXTLocator;
    private final Locator loginBTNLocator;

    public LoginPage(Page page) {
        this.page = page;
        createNewUserLocator = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("اضغط هنا لتسجيل حساب جديد"));
        nationalIdTXTLocator = page.locator("#txtNatN0");
        passwordTXTLocator = page.locator("#txtPassw0rd");
        loginBTNLocator = page.locator("#btnLogin");
    }

    public OTPPage login() throws JsonProcessingException {
        APIs apis = new APIs("xgzrxnadyoxr0f2scs4kkads");
        nationalIdTXTLocator.fill(nationalId);
        passwordTXTLocator.fill(passwd);
        loginBTNLocator.click();
        return new OTPPage(page);
    }

    public CreateNewUserPage goToCreateNewUser(){
        createNewUserLocator.click();
        return new CreateNewUserPage(page);
    }
}
