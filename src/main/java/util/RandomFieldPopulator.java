package util;

import com.github.javafaker.Faker;

public class RandomFieldPopulator {

    public static String generateFirstName() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }

    public static String generateLastName() {
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        return lastName;
    }

    public static String generateEmail() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String number = String.valueOf(faker.number().numberBetween(10000, 10000));
        return firstName + number + "@mail.com";
    }

    public static String generatePassword() {
        Faker faker = new Faker();
        String number = String.valueOf(faker.number().numberBetween(10000, 10000));
        return number;
    }
}
