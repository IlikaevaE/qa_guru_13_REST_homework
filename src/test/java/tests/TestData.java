package tests;

import com.github.javafaker.Faker;

public class TestData {

    static Faker faker = new Faker();
    public final static String FIRST_NAME = faker.address().firstName(),
            LAST_NAME = faker.address().lastName(),
            EMAIL = faker.internet().emailAddress(),
            PASSWORD = faker.internet().password(),
            AUTH_COOKIE_NAME = "NOPCOMMERCE.AUTH",
            EMAIL_AUTH = "rest.api@net.com",
            PASSWORD_AUTH = "@DPm2E!nG97mL",
            NEW_EMAIL = "rest.rest@mail.de";



}
