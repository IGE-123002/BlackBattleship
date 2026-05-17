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
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        assertTrue(loginPage.ensureLoggedIn(VALID_USERNAME, VALID_PASSWORD),
                "Não foi possível autenticar o utilizador antes de testar as armas.");

        mainPage.openGame();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("US10 – Uso de Armas Especiais")
    void usoDeArmasEspeciais() {
        // Limpar anúncios intersticiais antes de interagir
        mainPage.dismissOverlays();

        mainPage.specialWeaponsMenu.shouldBe(Condition.visible).click();
        mainPage.bigMissileOption.shouldBe(Condition.visible).click();

        $("body").shouldHave(Condition.or("confirmação da arma",
                Condition.text("missile"),
                Condition.text("míssil"),
                Condition.text("special"),
                Condition.text("weapon")));
    }
}