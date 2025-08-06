package test.ui.qDSP;

import apis.APIs;
import com.fasterxml.jackson.core.JsonProcessingException;
import driver.PlaywrightManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.DSP.CreateNewUserPage;
import page.DSP.LoginPage;
import page.DSP.OTPPage;

import java.io.IOException;

public class CreateNewUserTest {
    PlaywrightManager playwrightManager;
    private LoginPage loginPage;
    private CreateNewUserPage createNewUserPage;
    private OTPPage otpPage;

    @BeforeMethod
    public void setUp() {
        // Initialize Playwright and navigate to the login page
        playwrightManager = new PlaywrightManager();
        loginPage = new LoginPage(playwrightManager.getPage());
        playwrightManager.getPage().navigate("https://q-line-iis.alqemam.com/qDSP_Test_v14.3.01");
        createNewUserPage = loginPage.goToCreateNewUser();

    }

    @Test
    public void tc_createNewUser() throws InterruptedException, JsonProcessingException {
        otpPage = createNewUserPage.createNewUser();
//        otpPage.verifyOTP();
        createNewUserPage.completeInfo();

    }
    @AfterTest
    public void dontCloseBrowser() throws IOException {
        playwrightManager.getPage().close();
        playwrightManager.getBrowser().close();
    }
}
