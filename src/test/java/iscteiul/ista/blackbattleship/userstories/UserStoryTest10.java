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
 * Testes de aceitação para a User Story 10 – Uso de Armas Especiais.
 * Valida a funcionalidade de selecionar e utilizar armas especiais durante o jogo.
 */
public class UserStoryTest10 {

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

        // Garantir Login!
        assertTrue(loginPage.ensureLoggedIn(VALID_USERNAME, VALID_PASSWORD),
                "Não foi possível autenticar o utilizador antes de testar as armas.");

        mainPage.openGame();
    }

    @Test
    @DisplayName("US10 – Uso de Armas Especiais")
    void usoDeArmasEspeciais() {
        // 1. Abrir o menu de armas especiais
        mainPage.specialWeaponsMenu.shouldBe(Condition.visible).click();

        // 2. Clicar no míssil grande
        mainPage.bigMissileOption.shouldBe(Condition.visible).click();

        // 3. Validar a reação visual no DOM
        $("body").shouldHave(Condition.or("confirmação da arma",
                Condition.text("missile"),
                Condition.text("míssil"),
                Condition.text("special"),
                Condition.text("weapon")));
    }
}