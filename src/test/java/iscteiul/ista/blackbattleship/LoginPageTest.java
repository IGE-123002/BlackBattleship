package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPageTest {

    LoginPage loginPage = new LoginPage();

    private static final String VALID_USERNAME = "boaspessoal12345678@gmail.com";
    private static final String VALID_PASSWORD = "Manuel123";
    private static final String WRONG_PASSWORD = "password_errada_123";

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://papergames.io/en/");
        sleep(2000);


        if ($("button.fc-cta-consent").isDisplayed()) {
            executeJavaScript("arguments[0].click();", $("button.fc-cta-consent"));
        }
    }

    @BeforeEach
    public void resetPage() {
        open("https://papergames.io/en/");
        sleep(1000);


        if (loginPage.userAvatar.isDisplayed()) {
            executeJavaScript("arguments[0].click();", loginPage.userAvatar);
            sleep(600);
            if (loginPage.logoutButton.isDisplayed()) {
                executeJavaScript("arguments[0].click();", loginPage.logoutButton);
                sleep(1500);
            }
        }

        if (loginPage.acceptCookiesButton.isDisplayed()) {
            executeJavaScript("arguments[0].click();", loginPage.acceptCookiesButton);
        }
    }

    @Test
    public void loginModalOpens() {
        loginPage.openLoginModalButton.shouldBe(visible).click();
        sleep(500);
        loginPage.usernameInput.shouldBe(visible);
        loginPage.passwordInput.shouldBe(visible);
    }

    @Test
    public void loginWithValidCredentials() {
        loginPage.openLoginModalButton.shouldBe(visible).click();
        sleep(500);

        loginPage.usernameInput.shouldBe(visible).setValue(VALID_USERNAME);
        loginPage.passwordInput.shouldBe(visible).setValue(VALID_PASSWORD);
        executeJavaScript("arguments[0].click();", loginPage.submitButton);

        sleep(2000);

        if ($("button.fc-cta-consent").isDisplayed()) {
            executeJavaScript("arguments[0].click();", $("button.fc-cta-consent"));
        }

        loginPage.userAvatar.shouldBe(visible);
    }

    @Test
    public void loginWithWrongPasswordShowsError() {
        loginPage.openLoginModalButton.shouldBe(visible).click();
        sleep(500);

        loginPage.usernameInput.shouldBe(visible).setValue(VALID_USERNAME);
        loginPage.passwordInput.shouldBe(visible).setValue(WRONG_PASSWORD);
        executeJavaScript("arguments[0].click();", loginPage.submitButton);


        loginPage.errorMessage.shouldBe(visible);
        loginPage.userAvatar.shouldNotBe(visible);
    }

    @Test
    public void loginWithEmptyFieldsButtonIsDisabled() {
        loginPage.openLoginModalButton.shouldBe(visible).click();
        sleep(500);

        loginPage.submitButton.shouldBe(disabled);
        loginPage.userAvatar.shouldNotBe(visible);
    }
}