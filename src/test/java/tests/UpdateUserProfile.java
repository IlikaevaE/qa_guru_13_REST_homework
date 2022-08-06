package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.CustomApiListeners.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static tests.TestData.*;

public class UpdateUserProfile extends TestBase{
    @Test
    @DisplayName("Change user data in the user profile")
    void changeUserData() {
        String cookieKey = "NOPCOMMERCE.AUTH";
        String cookieValue = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .log().all()
                .formParam("Email", EMAIL_AUTH)
                .formParam("Password", PASSWORD_AUTH)
                .when()
                .post("http://demowebshop.tricentis.com/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(cookieKey);
        open("");
        Cookie authCookie = new Cookie(cookieKey, cookieValue);
        getWebDriver().manage().addCookie(authCookie);
        open("/customer/info");
        $("#FirstName").setValue(FIRST_NAME);
        $("#LastName").setValue(LAST_NAME);
        $("[name='save-info-button']").click();
        $("#FirstName").shouldHave(Condition.value(FIRST_NAME));
    }

}
