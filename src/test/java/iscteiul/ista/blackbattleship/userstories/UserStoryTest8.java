package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import iscteiul.ista.blackbattleship.pages.BattleshipMainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes de aceitação para a User Story 08 – Matchmaking Aleatório.
 * Valida a funcionalidade de jogar online contra um adversário aleatório.
 */
public class UserStoryTest8 {

    private static final String VALID_USERNAME = "boaspessoal12345678@gmail.com";
    private static final String VALID_PASSWORD = "Manuel123";

    private BattleshipMainPage mainPage;
    private LoginPage loginPage;

    @BeforeAll
    static void setUpAll() {
        BattleshipMainPage.configureBrowser();
    }

    @BeforeEach
    void setUp() {
        // Inicializar os Page Objects
        loginPage = Selenide.page(LoginPage.class);
        mainPage = Selenide.page(BattleshipMainPage.class);

        // O SEGREDO QUE FALTAVA: Fazer login antes de tentar jogar online!
        assertTrue(loginPage.ensureLoggedIn(VALID_USERNAME, VALID_PASSWORD),
                "Não foi possível autenticar o utilizador antes do matchmaking.");

        mainPage.openGame();
    }

    @Test
    @DisplayName("US08 – Matchmaking Aleatório")
    void matchmakingAleatorio() {
        // 1. Clicar no botão de Matchmaking
        mainPage.playOnlineButton.shouldBe(Condition.visible).click();

        // 2. Validar que entrou num estado de Matchmaking
        try {
            webdriver().shouldHave(urlContaining("/en/r/"));
        } catch (AssertionError e) {
            $("body").shouldHave(Condition.or("estado de fila de espera",
                    Condition.text("searching"),
                    Condition.text("waiting"),
                    Condition.text("match"),
                    Condition.text("procurando"),
                    Condition.text("aguardando")));
        }
    }
}