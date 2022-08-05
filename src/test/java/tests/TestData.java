package tests;

import com.github.javafaker.Faker;

public class TestData {

    static Faker faker = new Faker();
    public final static String GENDER = "F",
            FIRST_NAME = faker.address().firstName(),
            LAST_NAME = faker.address().lastName(),
            EMAIL = faker.internet().emailAddress(),
            PASSWORD = faker.internet().password();


}
