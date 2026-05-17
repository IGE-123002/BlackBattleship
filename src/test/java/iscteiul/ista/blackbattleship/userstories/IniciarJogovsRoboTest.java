package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.pages.GamePage;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.junit.jupiter.api.Assertions.*;

/**
 * US05 – Selecionar "Jogar contra o Bot".
 *
 * <p>Enquanto utilizador, quero selecionar a opção "Play vs Robot" para iniciar
 * imediatamente uma partida de treino, e poder abortá-la caso necessário.</p>
 *
 * <p>Passos automatizados:</p>
 * <ol>
 *   <li>Abrir a página principal do Battleship.</li>
 *   <li>Clicar em "Play vs robot".</li>
 *   <li>Verificar que o jogo iniciou – o URL muda para {@code /en/r/<code>}.</li>
 *   <li>Clicar no botão de retroceder do browser para acionar o diálogo de saída.</li>
 *   <li>Clicar em "Abort game" para terminar a sessão.</li>
 *   <li>Verificar que regressamos à página base do Battleship.</li>
 * </ol>
 */
class IniciarJogovsRoboTest {

    @BeforeAll
    static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("https://papergames.io/en/battleship");
    }

    /**
     * Verifica que clicar em "Play vs robot" inicia uma partida (URL altera-se),
     * e que é possível abortá-la através do diálogo de confirmação.
     */
    @Test
    void testPlayVsRobotAndAbort() {
        // Clicar em "Play vs robot"
        $$("button.btn-lg").findBy(text("Play vs robot")).click();

        // Verificar que o jogo iniciou – URL contém /en/r/
        webdriver().shouldHave(urlContaining("/en/r/"), Duration.ofSeconds(10));
        String gameUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(
                gameUrl.matches("https://papergames\\.io/en/r/[A-Za-z0-9]+"),
                "O URL do jogo deve seguir o padrão /en/r/<código>"
        );

        // Retroceder para acionar o diálogo de confirmação de saída
        back();

        // Confirmar o diálogo e abortar o jogo
        GamePage gamePage = new GamePage();
        gamePage.confirmAbort();

        // Verificar regresso à página principal do Battleship
        webdriver().shouldHave(urlContaining("/en/battleship"), Duration.ofSeconds(5));
    }
}
