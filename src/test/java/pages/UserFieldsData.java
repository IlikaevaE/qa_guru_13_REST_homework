package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.CustomApiListeners.withCustomTemplates;
import static io.restassured.RestAssured.given;


public class UserFieldsData {
    String requestVerificationTokenName = "__RequestVerificationToken";
    String requestVerificationTokenValue = "qofDFvPyyHpeN83iEwlm3RuVraXlhoMcyCuZpIXyS-nOIm6rU_Q3_A3B_vHg8NZpVqflT2W_NvUuOUw1l3romBlF6uP6cMr-3oV9PgYDyPk1";
    String requestVerificationTokenFormParamValue = "zt4a4PcUx53IWinpDeshrpbh0RXQlNAn5R3ocazIonrBx174IfEQPVF1eEZnRNozTtYnbgIt0iGBwuNXnDm95zHdXsNmW2B2-_YrMsyWs6fmBbd6iHzGd7v3hUx8Ok_P0";


    @Step("Open the page for user profile updating")
    public void openUserProfile() {
        open("http://demowebshop.tricentis.com/customer/info");
    }

    @Step("Updating user email address")
    public void updateEmailAddress(Cookie authCookie, String firstName, String lastName, String email) {
        given()
                .contentType("application/x-www-form-urlencoded")
                .filter(withCustomTemplates())
                .cookie(String.valueOf(authCookie))
                .cookie(requestVerificationTokenName, requestVerificationTokenValue)
                .formParam(requestVerificationTokenName, requestVerificationTokenFormParamValue)
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", email)
                .when()
                .post("http://demowebshop.tricentis.com/customer/info")
                .then()
                .log().all()
                .statusCode(302);
    }
    @Step("Checking the results Web test")
    public void checkResultUserProfile(String firstName) {
        $("#FirstName").shouldHave(Condition.attribute("value", firstName));

    }
}
