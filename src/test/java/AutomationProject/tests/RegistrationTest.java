// Java
package AutomationProject.tests;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationProject.pageObjects.RegistrationPage;
import AutomationProject.tests.testComponents.BaseTest;
import java.time.Duration;

// Import your RegistrationTestDataGenerator utility
import AutomationProject.utils.RegistrationTestDataGenerator;

public class RegistrationTest extends BaseTest {

    @Test(groups = {"Registration"})
    public void successfulRegistration() throws IOException {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.clickRegisterLink();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));

        String firstName = RegistrationTestDataGenerator.randomFirstName();
        String lastName = RegistrationTestDataGenerator.randomLastName();
        String email = RegistrationTestDataGenerator.randomEmail();
        String phone = RegistrationTestDataGenerator.randomPhone();
        String occupation = RegistrationTestDataGenerator.randomOccupation();
        String gender = RegistrationTestDataGenerator.randomGender();
        String password = RegistrationTestDataGenerator.randomPassword();

        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
        registrationPage.enterPhone(phone);
        registrationPage.selectOccupation(occupation);
        registrationPage.selectGender(gender);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.checkAgeCheckbox();
        registrationPage.clickRegister();

        String successMsg = registrationPage.getSuccessMessage();
        Assert.assertTrue(successMsg.contains("Account Created Successfully") || getDriver().getCurrentUrl().contains("login"),
                "Registration did not succeed as expected.");
    }
}