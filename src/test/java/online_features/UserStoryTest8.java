package online_features;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import iscteiul.ista.blackbattleship.pages.BattleshipMainPage;
import iscteiul.ista.blackbattleship.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        loginPage = Selenide.page(LoginPage.class);
        mainPage = Selenide.page(BattleshipMainPage.class);

        assertTrue(loginPage.ensureLoggedIn(VALID_USERNAME, VALID_PASSWORD),
                "Não foi possível autenticar o utilizador antes do matchmaking.");

        mainPage.openGame();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("US08 – Matchmaking Aleatório")
    void matchmakingAleatorio() {
        // Limpar anúncios intersticiais antes de interagir
        mainPage.dismissOverlays();

        mainPage.playOnlineButton.shouldBe(Condition.visible).click();

        try {
            webdriver().shouldHave(urlContaining("/en/r/"));
        } catch (AssertionError e) {
            $("body").shouldHave(Condition.or("estado de fila de espera ou jogo iniciado",
                    Condition.text("searching"),
                    Condition.text("waiting"),
                    Condition.text("match"),
                    Condition.text("Finding"),
                    Condition.text("random player"),
                    Condition.text("procurando"),
                    Condition.text("aguardando"),
                    Condition.text("Game started!"),
                    Condition.text("Your boats"),
                    Condition.text("opponent's boats")));
        }
    }
}