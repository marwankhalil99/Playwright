package test.ui.qDSP;

import com.fasterxml.jackson.core.JsonProcessingException;
import driver.PlaywrightManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.DSP.HomePage;
import page.DSP.LoginPage;

import java.io.IOException;

import static page.DSP.CreateNewUserPage.email;

public class LoginTest {
    PlaywrightManager playwrightManager;
    private page.DSP.LoginPage loginPage;
    private page.DSP.CreateNewUserPage createNewUserPage;
    private page.DSP.HomePage homePage;
    private page.DSP.OTPPage otpPage;

    @BeforeTest
    public void setUp() throws JsonProcessingException, InterruptedException {
        // Initialize Playwright and navigate to the login page
        playwrightManager = new PlaywrightManager();
        loginPage = new LoginPage(playwrightManager.getPage());
        playwrightManager.getPage().navigate("https://q-line-iis.alqemam.com/qDSP_Test_v14.3.01");
        createNewUserPage = loginPage.goToCreateNewUser();
        otpPage = createNewUserPage.createNewUser();
        createNewUserPage = otpPage.verifyOTPToCreateUser();
        createNewUserPage.completeInfo();
        playwrightManager.getPage().navigate("https://q-line-iis.alqemam.com/qDSP_Test_v14.3.01");
    }
    @Test
    public void tc_loginWithValidUser() throws JsonProcessingException, InterruptedException {
        otpPage = loginPage.login();
        homePage = otpPage.verifyOTPToLogin();
//        Assert.assertTrue(homePage.isHomePageDisplayed());
        Assert.assertEquals((homePage.getUserInfo())[0],"Test Test Test Test");
        Assert.assertEquals((homePage.getUserInfo())[1],email);

    }
    @AfterTest
    public void dontCloseBrowser() throws IOException {
//        playwrightManager.getPage().close();
//        playwrightManager.getBrowser().close();
        System.in.read();
    }

}
