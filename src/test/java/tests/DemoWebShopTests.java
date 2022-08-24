package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static tests.TestData.*;

public class DemoWebShopTests extends TestBase {

    @Test
    @DisplayName("Регистрация пользователя")
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
    @DisplayName("Login API Test + change data in user profile")
    void updateUserProfile() {

        Cookie authCookie = authorizationPage.authUserTest();

        authorizationPage.openLoginPage();
        getWebDriver().manage().addCookie(authCookie);
        open("http://demowebshop.tricentis.com");
        authorizationPage.checkUserAuthorization();
        open("http://demowebshop.tricentis.com/customer/info");
        $("#FirstName").setValue(FIRST_NAME);
        $("#LastName").setValue(LAST_NAME);
        $("[name='save-info-button']").click();
        $("#FirstName").shouldHave(Condition.value(FIRST_NAME));
    }
}

