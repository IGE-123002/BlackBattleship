package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object para a página de login usando Page Factory.
 */
public class LoginPage {

    private static final String HOME_URL = "https://papergames.io/en/";

    @FindBy(css = "button.fc-cta-consent")
    public SelenideElement acceptCookiesButton;

    @FindBy(xpath = "//button[contains(., 'Log in') or contains(., 'Login')]")
    public SelenideElement openLoginModalButton;

    @FindBy(css = "input[type='email']")
    public SelenideElement usernameInput;

    @FindBy(css = "input[type='password']")
    public SelenideElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public SelenideElement submitButton;

    // Substitui a antiga declaração por esta:
    @FindBy(css = "[class*='avatar'], [src*='avatar'], a[href*='/profile/']")
    public SelenideElement userAvatar;

    // AS DUAS VARIÁVEIS QUE FALTAVAM:
    @FindBy(xpath = "//button[contains(., 'Log out') or contains(., 'Logout') or contains(., 'Sair')]")
    public SelenideElement logoutButton;

    @FindBy(css = "[class*='error']") // Classe genérica para mensagens de erro no site
    public SelenideElement errorMessage;

    public void openHomePage() {
        open(HOME_URL);

        // Esperamos 1.5 segundos propositadamente para dar tempo
        // ao script do site de carregar e mostrar o banner de cookies.
        sleep(1500);

        if (acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    public boolean ensureLoggedIn(String username, String password) {
        openHomePage();

        if (isLoggedIn()) {
            return true;
        }

        openLoginModalButton.shouldBe(Condition.visible).click();
        usernameInput.shouldBe(Condition.visible).setValue(username);
        passwordInput.shouldBe(Condition.visible).setValue(password);
        submitButton.shouldBe(Condition.visible).click();

        // Espera de segurança para o site processar o login e recarregar
        sleep(2500);

        // Limpar cookies teimosos que surjam pós-login
        if (acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }

        // Em vez de forçar a procura só do avatar (que pode falhar se não houver foto),
        // o Selenide espera até que o botão de "Login" desapareça do ecrã!
        openLoginModalButton.shouldNotBe(Condition.visible);

        return isLoggedIn();
    }

    public boolean isLoggedIn() {
        // Estamos logados se o botão de entrar já não estiver visível na navbar
        return !openLoginModalButton.isDisplayed();
    }
}