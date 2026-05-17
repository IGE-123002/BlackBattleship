package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPageTest {

    // 1. Apenas declarar a variável aqui. NÃO usar "new LoginPage()"
    LoginPage loginPage;

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
        // 2. Inicializar a página corretamente através do Page Factory do Selenide
        loginPage = Selenide.page(LoginPage.class);

        open("https://papergames.io/en/");

        // O Selenide é inteligente: verifica se o banner existe e clica sem precisar de JavaScript
        if (loginPage.acceptCookiesButton.exists()) {
            loginPage.acceptCookiesButton.click();
        }

        // Fazer logout se o teste anterior deixou a sessão aberta
        if (loginPage.userAvatar.exists() && loginPage.userAvatar.isDisplayed()) {
            loginPage.userAvatar.click();
            if (loginPage.logoutButton.exists()) {
                loginPage.logoutButton.click();
            }
        }
    }

    @Test
    public void loginModalOpens() {
        loginPage.openLoginModalButton.shouldBe(visible).click();

        loginPage.usernameInput.shouldBe(visible);
        loginPage.passwordInput.shouldBe(visible);
    }

    @Test
    public void loginWithValidCredentials() {
        loginPage.openLoginModalButton.shouldBe(visible).click();

        loginPage.usernameInput.shouldBe(visible).setValue(VALID_USERNAME);
        loginPage.passwordInput.shouldBe(visible).setValue(VALID_PASSWORD);
        loginPage.submitButton.click();

        // Limpar cookies adicionais que possam surgir pós-login
        if ($("button.fc-cta-consent").exists()) {
            $("button.fc-cta-consent").click();
        }

        // Validar que o avatar apareceu (login com sucesso)
        loginPage.userAvatar.shouldBe(visible);
    }

    @Test
    public void loginWithWrongPasswordShowsError() {
        loginPage.openLoginModalButton.shouldBe(visible).click();

        loginPage.usernameInput.shouldBe(visible).setValue(VALID_USERNAME);
        loginPage.passwordInput.shouldBe(visible).setValue(WRONG_PASSWORD);
        loginPage.submitButton.click();

        // Validar erro
        loginPage.errorMessage.shouldBe(visible);
        loginPage.userAvatar.shouldNotBe(visible);
    }

    @Test
    public void loginWithEmptyFieldsButtonIsDisabled() {
        loginPage.openLoginModalButton.shouldBe(visible).click();

        loginPage.submitButton.shouldBe(disabled);
        loginPage.userAvatar.shouldNotBe(visible);
    }
}