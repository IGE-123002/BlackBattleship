package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.pages.AccountPage;
import iscteiul.ista.blackbattleship.pages.BattleshipHomePage;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

/**
 * US01 – Definir Nickname / Alterar Display Name.
 *
 * <p>Enquanto utilizador, quero alterar o meu display name em "My Account"
 * para ser devidamente identificado nas partidas.</p>
 *
 * <p>Passos automatizados:</p>
 * <ol>
 *   <li>Abrir a página principal do Battleship.</li>
 *   <li>Clicar no botão de perfil (canto superior esquerdo).</li>
 *   <li>Selecionar "My Account" no menu que abre.</li>
 *   <li>Alterar o campo "Display Name" para "Rui Coelho".</li>
 *   <li>Clicar em "Update".</li>
 *   <li>Verificar que aparece o toast "Profile updated successfully."</li>
 * </ol>
 */
class DefinirNicknameTest {

    private static final String ORIGINAL_NAME = "ralcao";
    private static final String NEW_NAME = "Rui Coelho";

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
     * Restaura o nome original após cada teste para garantir repetibilidade.
     */
    @AfterEach
    void tearDown() {
        open("https://papergames.io/en/account");
        AccountPage page = new AccountPage();
        page.setDisplayName(ORIGINAL_NAME);
        page.clickUpdate();
    }

    /**
     * Verifica que o utilizador consegue alterar o display name através do menu de perfil
     * e que o sistema confirma a operação com o toast de sucesso.
     */
    @Test
    void testChangeDisplayName() {
        BattleshipHomePage homePage = new BattleshipHomePage();
        AccountPage accountPage = homePage.goToMyAccount();

        accountPage.setDisplayName(NEW_NAME);
        accountPage.clickUpdate();

        accountPage.successToast.shouldBe(visible, Duration.ofSeconds(5));
        accountPage.successToast.shouldHave(text("Profile updated successfully"));
    }
}
