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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserStoryTest6 {

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
                "Não foi possível autenticar o utilizador antes de gerar o convite.");

        mainPage.openGame();
    }

    @AfterEach
    void tearDown() {
        // Força o fecho do browser para o próximo teste começar num ambiente 100% limpo
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("US06 – Convite para Amigo")
    void conviteParaAmigo() {
        // Limpar anúncios intersticiais antes de interagir
        mainPage.dismissOverlays();

        mainPage.inviteFriendButton.shouldBe(Condition.visible).click();
        Selenide.sleep(3000);

        String inviteLink = mainPage.getGeneratedInviteLink();
        assertNotNull(inviteLink, "O link de convite não foi gerado pelo JavaScript do site.");
        assertTrue(inviteLink.contains("http") || inviteLink.contains("papergames"),
                "O link gerado '" + inviteLink + "' não é um URL válido.");
    }
}