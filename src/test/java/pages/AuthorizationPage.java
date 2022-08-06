package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.CustomApiListeners.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static tests.TestData.*;

public class AuthorizationPage {
    @Step("Open the login page")
    public void openLoginPage() {
        open("http://demowebshop.tricentis.com/login");
    }

    @Step("User authorization API test")
    public Cookie authUserTest(String email, String password) {
        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded")
                .filter(withCustomTemplates())
                .formParam("Email", email)
                .formParam("Password", password)
                .when()
                .post("http://demowebshop.tricentis.com/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(AUTH_COOKIE_NAME);

        return new Cookie(AUTH_COOKIE_NAME, authCookieValue);
    }

    @Step("Checking the results Web test")
    public void checkUserAuthorization() {
        $("a.account").shouldHave(Condition.text(EMAIL_AUTH));
    }
}
