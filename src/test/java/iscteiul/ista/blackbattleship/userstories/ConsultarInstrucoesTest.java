package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.pages.BattleshipHomePage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

/**
 * US02 – Visualizar Instruções do Jogo.
 *
 * <p>Enquanto utilizador, quero visualizar a secção de instruções na página principal
 * para compreender as regras desta versão do jogo.</p>
 *
 * <p>Passos automatizados:</p>
 * <ol>
 *   <li>Abrir a página principal do Battleship.</li>
 *   <li>Fazer scroll até à secção de regras.</li>
 *   <li>Verificar que os blocos de texto com as regras estão visíveis acima do footer.</li>
 * </ol>
 */
class ConsultarInstrucoesTest {

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
     * Verifica que a secção de regras é visível após scroll e que o cabeçalho está correto.
     */
    @Test
    void testRulesSectionHeadingIsVisible() {
        BattleshipHomePage page = new BattleshipHomePage();

        page.rulesSection.scrollIntoView(true);
        page.rulesHeading.shouldBe(visible);
        assertEquals("Rules of Battleship game online", page.rulesHeading.getText());
    }

    /**
     * Verifica que a secção de regras aparece antes (acima de) do footer na página.
     */
    @Test
    void testRulesSectionIsAboveFooter() {
        BattleshipHomePage page = new BattleshipHomePage();

        page.rulesSection.scrollIntoView(true);
        page.rulesSection.shouldBe(visible);
        page.footer.shouldBe(exist);

        int rulesY  = page.rulesSection.getLocation().getY();
        int footerY = page.footer.getLocation().getY();
        assertTrue(rulesY < footerY, "A secção de regras deve aparecer acima do footer");
    }
}
