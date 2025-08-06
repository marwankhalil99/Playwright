package page.DSP;

import apis.APIs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static page.DSP.CreateNewUserPage.phoneNum;

public class OTPPage {
    Page page;
    //----------------- OTP
    private final Locator firstInTXTLocator;
    private final Locator secondInTXTLocator;
    private final Locator thirdInTXTLocator;
    private final Locator forthInTXTLocator;
    private final Locator fifthInTXTLocator;
    private final Locator confirmOtpBTNLocator;

    public OTPPage(Page page) {
        this.page = page;
        firstInTXTLocator = page.locator("#first-input");
        secondInTXTLocator = page.locator("#second-input");
        thirdInTXTLocator = page.locator("#third-input");
        forthInTXTLocator = page.locator("#fourth-input");
        fifthInTXTLocator = page.locator("#fifth-input");
        confirmOtpBTNLocator = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("اضغط هنا للتأكيد"));
    }
    public HomePage verifyOTPToLogin() throws JsonProcessingException, InterruptedException {
        APIs apis = new APIs("xgzrxnadyoxr0f2scs4kkads");
        String otp = apis.getOTP(phoneNum);
        firstInTXTLocator.fill(String.valueOf(otp.charAt(0)));
        secondInTXTLocator.fill(String.valueOf(otp.charAt(1)));
        thirdInTXTLocator.fill(String.valueOf(otp.charAt(2)));
        forthInTXTLocator.fill(String.valueOf(otp.charAt(3)));
        fifthInTXTLocator.fill(String.valueOf(otp.charAt(4)));
        confirmOtpBTNLocator.click();
        return new HomePage(page);
    }
    public CreateNewUserPage verifyOTPToCreateUser() throws JsonProcessingException, InterruptedException {
        APIs apis = new APIs("xgzrxnadyoxr0f2scs4kkads");
        String otp = apis.getOTP(phoneNum);
        firstInTXTLocator.fill(String.valueOf(otp.charAt(0)));
        secondInTXTLocator.fill(String.valueOf(otp.charAt(1)));
        thirdInTXTLocator.fill(String.valueOf(otp.charAt(2)));
        forthInTXTLocator.fill(String.valueOf(otp.charAt(3)));
        fifthInTXTLocator.fill(String.valueOf(otp.charAt(4)));
        confirmOtpBTNLocator.click();
        return new CreateNewUserPage(page);
    }

}
