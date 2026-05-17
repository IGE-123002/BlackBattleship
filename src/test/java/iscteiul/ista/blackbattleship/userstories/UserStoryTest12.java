package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import iscteiul.ista.blackbattleship.pages.BattleshipMainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes de aceitação para a User Story 12 – Criação de Torneios.
 * Valida a funcionalidade de criar um torneio de batalha naval.
 */
public class UserStoryTest12 {

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
        // Inicializar os Page Objects com Selenide Page Factory
        loginPage = Selenide.page(LoginPage.class);
        mainPage = Selenide.page(BattleshipMainPage.class);

        // Garantir Login antes de tentar criar um torneio!
        assertTrue(loginPage.ensureLoggedIn(VALID_USERNAME, VALID_PASSWORD),
                "Não foi possível autenticar o utilizador antes de testar a criação de torneios.");

        mainPage.openGame();
    }

    @Test
    @DisplayName("US12 – Criação de Torneios")
    void criacaoDeTorneios() {
        // 1. Clicar no menu de Torneios
        mainPage.tournamentMenu.shouldBe(Condition.visible).click();

        // 2. Preencher os campos do formulário (o Selenide espera automaticamente que eles apareçam)
        mainPage.tournamentNameInput.shouldBe(Condition.visible).setValue("Torneio de Teste do Dinis");
        mainPage.tournamentDateInput.shouldBe(Condition.visible).setValue("2026-05-17");
        mainPage.tournamentRulesInput.shouldBe(Condition.visible).setValue("Regras de teste automatizado usando Selenide.");

        // 3. Submeter o torneio
        mainPage.createTournamentSubmitButton.shouldBe(Condition.visible).click();

        // 4. Validar que a criação teve sucesso
        $("body").shouldHave(Condition.or("mensagem de sucesso do torneio",
                Condition.text("tournament"),
                Condition.text("torneio"),
                Condition.text("created"),
                Condition.text("success")));
    }
}