package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

public class DemoWebShopTests extends TestBase {
    @Test
    @DisplayName("Fill in the registration form UI test")
    void registerNewUser() {

        registrationFormPage
                .openPage()
                .setGender()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .clickSubmit();

        registrationFormPage
                .checkResult();
    }

    @Test
    @DisplayName("Login API Test")
    void userLogin() {

        Cookie authCookie = authorizationPage.authUserTest(EMAIL_AUTH,PASSWORD_AUTH);

        authorizationPage.openLoginPage();
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        open("http://demowebshop.tricentis.com");
        authorizationPage.checkUserAuthorization();
    }

    @Test
    @DisplayName("Change user email in the user profile")
    void changeUserEmail() {
        Cookie authCookie = authorizationPage.authUserTest(EMAIL_AUTH, PASSWORD_AUTH);

        userFieldsData.updateEmailAddress(authCookie, FIRST_NAME, LAST_NAME, EMAIL);

         open("http://demowebshop.tricentis.com");
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);

        userFieldsData.openUserProfile();

        userFieldsData.checkResultUserProfile(FIRST_NAME);

    }
}

