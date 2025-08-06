package test.ui.qCMS;

import apis.APIs;
import com.fasterxml.jackson.core.JsonProcessingException;
import driver.PlaywrightManager;
import org.testng.annotations.Test;
import page.qCMS.LoginPage;

public class LoginTest {
    LoginPage loginPage;
    PlaywrightManager playwrightManager;
    //@BeforeMethod
    public void setUp() {
        // Initialize Playwright and navigate to the login page
        playwrightManager = new PlaywrightManager();
        loginPage = new LoginPage(playwrightManager.getPage());
        playwrightManager.getPage().navigate("https://q-line-iis.alqemam.com/qcms_test_v14.3.16/login.aspx");
    }
    @Test
    public void testLogin() throws InterruptedException {
        // Perform login actions
        loginPage.enterUserId("0560560");
        loginPage.enterPassword("24602460");
        loginPage.rememberMeChk(true);
        loginPage.clickLoginButton();
        Thread.sleep(10000); // Wait for 2 seconds to observe the result
    }
    @Test
    public void testLogin1() throws InterruptedException {
        // Perform login actions
        loginPage.enterUserId("0560560");
        loginPage.enterPassword("24602460");
        loginPage.rememberMeChk(false);
        loginPage.clickLoginButton();
        Thread.sleep(10000); // Wait for 2 seconds to observe the result
    }

    @Test(description = "test description")
    public void testName() throws JsonProcessingException {
        APIs apIs = new APIs("xgzrxnadyoxr0f2scs4kkads");
        System.out.println(apIs.getOTP("966522454544"));

    }
}
