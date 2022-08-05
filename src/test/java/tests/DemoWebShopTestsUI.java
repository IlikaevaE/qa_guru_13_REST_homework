package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static tests.TestData.*;

public class DemoWebShopTestsUI extends TestBase {
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
}
