package util;

import com.github.javafaker.Faker;

public class RandomFieldPopulator {

    public static String generateEmail() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String number = String.valueOf(faker.number().numberBetween(10000, 10000));
        return firstName + number + "@mail.com";
    }
}
