package page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.ElementActions;

public class LoginPage {
    private final Page page;
    private final Locator userIdLocator;
    private final Locator passwordLocator;
    private final Locator loginButtonLocator;
    private final Locator remeberMeCheckboxLocator;

    public LoginPage(Page page) {
        this.page = page;
        this.userIdLocator = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("الرقم الوظيفي"));
        this.passwordLocator = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("كلمة المرور"));
        this.loginButtonLocator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("تسجيل الدخول"));
//        this.remeberMeCheckboxLocator = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("تذكرني"));
        this.remeberMeCheckboxLocator = page.locator("span.toggle");
    }
    public void enterUserId(String userId) {
        ElementActions.fillInput(userIdLocator,userId);
    }
    public void enterPassword(String password) {
        ElementActions.fillInput(passwordLocator, password);
    }
    public void clickLoginButton() {
        ElementActions.clickElement(loginButtonLocator);
    }
    public void rememberMeChk(Boolean isChecked) {
        if(isChecked) {
            remeberMeCheckboxLocator.check();
        }else {
            remeberMeCheckboxLocator.uncheck();
        }

    }

}


