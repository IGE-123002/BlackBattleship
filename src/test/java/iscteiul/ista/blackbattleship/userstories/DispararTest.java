package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.pages.GamePage;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

/**
 * US09 – Disparo na Grelha Adversária.
 *
 * <p>Enquanto jogador, durante o meu turno, quero clicar numa célula da grelha adversária
 * para efetuar um disparo e ver a reação visual correspondente (acerto ou erro).</p>
 *
 * <p>Pré-condição: jogo ativo contra o robot (continuação de US05).</p>
 *
 * <p>Passos automatizados:</p>
 * <ol>
 *   <li>Iniciar jogo contra o robot (via "Play vs robot").</li>
 *   <li>Aguardar que o grid adversário fique disponível.</li>
 *   <li>Clicar numa célula do grid adversário.</li>
 *   <li>Verificar que o {@code svg.intersection} da célula ganha a classe
 *       {@code no-hit} (erro) ou {@code hit} (acerto).</li>
 *   <li>Abortar o jogo no teardown.</li>
 * </ol>
 */
class DispararTest {

    @BeforeAll
    static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("https://papergames.io/en/battleship");
        $$("button.btn-lg").findBy(text("Play vs robot")).click();
        webdriver().shouldHave(urlContaining("/en/r/"), Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        // Abortar o jogo apenas se ainda estiver ativo
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        if (currentUrl.contains("/en/r/")) {
            back();
            new GamePage().confirmAbort();
        }
    }

    /**
     * Verifica que clicar numa célula do grid adversário produz uma reação visual:
     * o {@code svg.intersection} da célula passa a ter a classe {@code no-hit} ou {@code hit}.
     */
    @Test
    void testFireAtOpponentGridCell() {
        GamePage gamePage = new GamePage();

        // Aguardar que o SVG da célula adversária esteja visível
        SelenideElement cellSvg = gamePage.getFirstOpponentCellSvg();
        cellSvg.shouldBe(visible, Duration.ofSeconds(10));

        // Efetuar o disparo
        gamePage.fireAtFirstCell();

        // Verificar reação: SVG ganha classe 'no-hit' (erro) ou 'hit' (acerto)
        cellSvg.should(
                or("reação ao disparo", cssClass("no-hit"), cssClass("hit")),
                Duration.ofSeconds(5)
        );
    }
}
