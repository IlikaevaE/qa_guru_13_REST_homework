package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    SelenideElement
            genderInput = $("#gender-female"),
            firstNameInput = $("#FirstName"),
            lastNameInput = $("#LastName"),
            emailInput = $("#Email"),
            passwordInput = $("#Password"),
            confirmPasswordInput = $("#ConfirmPassword"),
            submitClick = $("#register-button");

    @Step("Open the page demowebshop")
    public RegistrationFormPage openPage() {
        open("/register");
       // $(".header-logo").shouldHave(Condition.text("Demo Web Shop"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    @Step("Choose gender")
    public RegistrationFormPage setGender() {
        genderInput.click();
        return this;
    }

    @Step("Enter user first name")
    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Enter user last name")
    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Enter user email")
    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Enter new password")
    public RegistrationFormPage setPassword(String value) {
        passwordInput.setValue(value);
        return this;
    }

    @Step("Confirm new password")
    public RegistrationFormPage setConfirmPassword(String value) {
        confirmPasswordInput.setValue(value);
        return this;
    }

    @Step("Click on the button Register")
    public RegistrationFormPage clickSubmit() {
        submitClick.click();
        return this;
    }
    @Step("Check the result of registration")
    public RegistrationFormPage checkResult() {
        $(".result").shouldHave(Condition.text("Your registration completed"));
        return this;
    }
}
