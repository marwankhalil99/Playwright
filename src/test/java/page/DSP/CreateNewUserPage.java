package page.DSP;

import apis.APIs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CreateNewUserPage {

    Page page;

    private final Locator firstNameTXTLocator;
    private final Locator secondNameTXTLocator;
    private final Locator thirdNameTXTLocator;
    private final Locator forthNameTXTLocator;
    private final Locator NationalNoTXTLocator;
    private final Locator phoneNumTXTLocator;
    private final Locator emailTXTLocator;
    private final Locator passwdTXTLocator;
    private final Locator passwdConfirmTXTLocator;
    private final Locator createNewUserBTNLocator;


    //----------------- OTP
    private final Locator firstInTXTLocator;
    private final Locator secondInTXTLocator;
    private final Locator thirdInTXTLocator;
    private final Locator forthInTXTLocator;
    private final Locator fifthInTXTLocator;
    private final Locator confirmOtpBTNLocator;

    //----------------- Continue entering data
    private final Locator addressTXTLocator;
    private final Locator postalCodeTXTLocator;
    private final Locator birthDateTXTLocator;
    private final Locator jobNameTXTLocator;
    private final Locator localPhoneTXTLocator;
    private final Locator maritalStatusDDLLocator;
    private final Locator numaridMaritalStatusDDLLocator;
    private final Locator saveAndContinueBTNLocator;


    static Faker faker = new Faker();
    static String phoneNum = "5" + faker.number().digits(8);
    public static String email = faker.internet().emailAddress();
    public static String passwd = "Test@"+phoneNum;
    public static String nationalId = "1"+ faker.number().digits(9);


    public CreateNewUserPage(Page page) {
        this.page = page;
        firstNameTXTLocator = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("الاسم الأول"));
        secondNameTXTLocator = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("الاسم الثانى"));
        thirdNameTXTLocator = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("الاسم الثالث"));
        forthNameTXTLocator = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("الاسم الرابع"));
        NationalNoTXTLocator = page.getByPlaceholder("ادخل رقم الهوية");
        phoneNumTXTLocator = page.getByPlaceholder("رقم الجوال");
        emailTXTLocator = page.getByPlaceholder("ادخل البريد الإلكتروني");
        passwdTXTLocator = page.getByPlaceholder("ادخل كلمة المرور");
        passwdConfirmTXTLocator = page.getByPlaceholder("برجاء إدخال تأكيد كلمة المرور");
        createNewUserBTNLocator = page.locator("#btnLogin");
        firstInTXTLocator = page.locator("#first-input");
        secondInTXTLocator = page.locator("#second-input");
        thirdInTXTLocator = page.locator("#third-input");
        forthInTXTLocator = page.locator("#fourth-input");
        fifthInTXTLocator = page.locator("#fifth-input");
        confirmOtpBTNLocator = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("اضغط هنا للتأكيد"));
        addressTXTLocator = page.locator("#txtAddress");
        postalCodeTXTLocator = page.locator("#txtPostalCode");
        birthDateTXTLocator = page.locator("#txtBirthDate");
        jobNameTXTLocator = page.locator("#txtJobName");
        localPhoneTXTLocator = page.getByPlaceholder("أدخل رقم الهاتف");
        maritalStatusDDLLocator = page.locator("#ddl-marital-status_ddlSelectButtonTarget");
        numaridMaritalStatusDDLLocator = page.locator("label:has-text('غير متزوج')");
        saveAndContinueBTNLocator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("اضغط هنا لحفظ ومتابعة"));

    }

    public OTPPage createNewUser(){
        firstNameTXTLocator.fill("Test");
        secondNameTXTLocator.fill("Test");
        thirdNameTXTLocator.fill("Test");
        forthNameTXTLocator.fill("Test");
        NationalNoTXTLocator.fill(nationalId);
        phoneNumTXTLocator.fill(phoneNum);
        emailTXTLocator.fill(email);
        passwdTXTLocator.fill(passwd);
        passwdConfirmTXTLocator.fill(passwd);
        createNewUserBTNLocator.click();
        System.out.println("National ID: "+nationalId);
        System.out.println("Phone Number: "+phoneNum);
        System.out.println("Email: "+email);
        System.out.println("Password: "+passwd);
        return new OTPPage(page);
    }

    public void completeInfo(){
        addressTXTLocator.fill("Test");
        postalCodeTXTLocator.fill("12345");
        birthDateTXTLocator.fill("1440/01/05");
        jobNameTXTLocator.fill("Test");
        localPhoneTXTLocator.fill("123456789");
        maritalStatusDDLLocator.click();
        numaridMaritalStatusDDLLocator.click();
        saveAndContinueBTNLocator.click();
    }


}
