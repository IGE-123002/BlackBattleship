package TestSuitUS01;

import iscteiul.ista.blackbattleship.US01Page;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class US01PageTest {

    US01Page us01Page;

    private static final String VALID_USERNAME = "boaspessoal12345678@gmail.com";
    private static final String VALID_PASSWORD = "Manuel123";
    private static final String WRONG_PASSWORD = "password_errada_123";

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void resetPage() {
        us01Page = Selenide.page(US01Page.class);

        open("https://papergames.io/en/");

        if (us01Page.acceptCookiesButton.exists()) {
            us01Page.acceptCookiesButton.click();
        }

        if (us01Page.userAvatar.exists() && us01Page.userAvatar.isDisplayed()) {
            us01Page.userAvatar.click();
            if (us01Page.logoutButton.exists()) {
                us01Page.logoutButton.click();
            }
        }
    }

    @Test
    public void loginModalOpens() {
        us01Page.openLoginModalButton.shouldBe(visible).click();
        us01Page.usernameInput.shouldBe(visible);
        us01Page.passwordInput.shouldBe(visible);
    }

    @Test
    public void loginWithValidCredentials() {
        us01Page.openLoginModalButton.shouldBe(visible).click();
        us01Page.usernameInput.shouldBe(visible).setValue(VALID_USERNAME);
        us01Page.passwordInput.shouldBe(visible).setValue(VALID_PASSWORD);
        us01Page.submitButton.click();

        if ($("button.fc-cta-consent").exists()) {
            $("button.fc-cta-consent").click();
        }

        us01Page.userAvatar.shouldBe(visible);
    }

    @Test
    public void loginWithWrongPasswordShowsError() {
        us01Page.openLoginModalButton.shouldBe(visible).click();
        us01Page.usernameInput.shouldBe(visible).setValue(VALID_USERNAME);
        us01Page.passwordInput.shouldBe(visible).setValue(WRONG_PASSWORD);
        us01Page.submitButton.click();

        us01Page.errorMessage.shouldBe(visible);
        us01Page.userAvatar.shouldNotBe(visible);
    }

    @Test
    public void loginWithEmptyFieldsButtonIsDisabled() {
        us01Page.openLoginModalButton.shouldBe(visible).click();
        us01Page.submitButton.shouldBe(disabled);
        us01Page.userAvatar.shouldNotBe(visible);
    }
}