package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.CustomApiListeners.withCustomTemplates;
import static io.restassured.RestAssured.given;
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

        Cookie authCookie = authorizationPage.authUserTest();

        authorizationPage.openLoginPage();
        getWebDriver().manage().addCookie(authCookie);
        open("http://demowebshop.tricentis.com");
        authorizationPage.checkUserAuthorization();
    }
}

