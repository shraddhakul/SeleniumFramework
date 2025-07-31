package AutomationProject.utils;

// Java
import java.util.Random;
import java.util.UUID;

public class RegistrationTestDataGenerator {
    private static final String[] OCCUPATIONS = {"Doctor", "Student", "Engineer", "Scientist"};
    private static final String[] GENDERS = {"Male", "Female"};
    private static final Random RANDOM = new Random();

// Java
public static String randomFirstName() {
    String base = "TestFirst";
    int maxRandomLength = 12 - base.length();
    int randomNum = maxRandomLength > 0 ? RANDOM.nextInt((int)Math.pow(10, maxRandomLength)) : 0;
    String firstName = base + randomNum;
    return firstName.substring(0, Math.min(firstName.length(), 12));
}

    public static String randomLastName() {
        return "TestLast" + RANDOM.nextInt(10000);
    }

    public static String randomEmail() {
        return "user" + UUID.randomUUID() + "@example.com";
    }

    public static String randomPhone() {
        return "9" + (RANDOM.nextInt(900000000) + 100000000);
    }

    public static String randomOccupation() {
        return OCCUPATIONS[RANDOM.nextInt(OCCUPATIONS.length)];
    }

    public static String randomGender() {
        return GENDERS[RANDOM.nextInt(GENDERS.length)];
    }

    public static String randomPassword() {
        return "Test@" + RANDOM.nextInt(10000);
    }
}
