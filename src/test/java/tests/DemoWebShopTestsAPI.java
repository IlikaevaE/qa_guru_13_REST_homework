package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.CustomApiListeners.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static tests.TestData.*;
import static tests.TestData.PASSWORD;

public class DemoWebShopTestsAPI extends TestBase{

    @Test
    @DisplayName("Fill in the registration form API test")
    void registerUserToWebShop() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        String authCookieName = "NOPCOMMERCE.AUTH";
        String authCookieValue = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded; charset=ISO-8859-1")
                .cookie("Nop.customer", "08d9f0c0-0948-48ae-bcf5-76c09d65f681")
                .formParam("Gender", GENDER)
                .formParam("FirstName", FIRST_NAME)
                .formParam("LastName", LAST_NAME)
                .formParam("Email", EMAIL)
                .formParam("Password", PASSWORD)
                .formParam("ConfirmPassword", PASSWORD)
                .formParam("register-button", "Register")
                .log().all()
                .when()
                .post("/register")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieName);

    }

    @Test
    @DisplayName(" ")
    void userLogin() {
      //  RestAssured.baseURI = "http://demowebshop.tricentis.com";
        String authCookieName = "NOPCOMMERCE.AUTH";
        String authCookieValue = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", "rest.api@net.com") // это body передаем из Network на странице
                .formParam("Password", "@DPm2E!nG97mL")
                .log().all()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieName);
        open("/login");
        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        //
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);

        given()
                .contentType("application/x-www-form-urlencoded")
                .cookie(authCookieName, authCookieValue)
                .formParam("FirstName", "Qwerty")
                .log().all()
                .when()
                .post("http://demowebshop.tricentis.com/customer/info")
                .then()
                .log().all()
                .statusCode(302);

        open("/customer/info");
        // Мы хотим открыть страницу и выполнить проверку
        open("");
        $(".FirstName").shouldHave(Condition.text("Qwerty"));
    }


}
