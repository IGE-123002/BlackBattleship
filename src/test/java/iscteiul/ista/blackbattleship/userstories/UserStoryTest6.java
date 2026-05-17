package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import iscteiul.ista.blackbattleship.pages.BattleshipMainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testes de aceitação para a User Story 06 – Convite para Amigo.
 */
class UserStoryTest6 {

    private static final String VALID_USERNAME = "boaspessoal12345678@gmail.com";
    private static final String VALID_PASSWORD = "Manuel123";

    private LoginPage loginPage;
    private BattleshipMainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        BattleshipMainPage.configureBrowser();
    }

    @BeforeEach
    void setUp() {
        // Inicializar os Page Objects seguindo o padrão exigido
        loginPage = Selenide.page(LoginPage.class);
        mainPage = Selenide.page(BattleshipMainPage.class);

        assertTrue(loginPage.ensureLoggedIn(VALID_USERNAME, VALID_PASSWORD),
                "Não foi possível autenticar o utilizador de teste antes de abrir o jogo.");

        mainPage.openGame();
    }

    @Test
    @DisplayName("US06 – Convite para Amigo")
    void conviteParaAmigo() {
        // 1. Clicar no botão de convite (o Selenide garante a espera e o clique)
        mainPage.inviteFriendButton.shouldBe(Condition.visible).click();

        // 2. Dar uma breve pausa (1 segundo) para o JavaScript da página gerar e mostrar o link
        com.codeborne.selenide.Selenide.sleep(1000);

        // 3. Ler o valor real diretamente do DOM
        String linkGerado = mainPage.getGeneratedInviteLink();

        // 4. Asserções Reais do JUnit
        org.junit.jupiter.api.Assertions.assertNotNull(linkGerado, "Não foi encontrado nenhum link de convite no ecrã.");
        org.junit.jupiter.api.Assertions.assertFalse(linkGerado.isBlank(), "O link de convite não pode estar vazio.");
        org.junit.jupiter.api.Assertions.assertTrue(linkGerado.contains("http") || linkGerado.contains("papergames"),
                "O valor encontrado não parece ser um link válido: " + linkGerado);
    }
}