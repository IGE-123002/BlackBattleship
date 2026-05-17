package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class MatchHistoryPageTest {

    MatchHistoryPage historyPage = new MatchHistoryPage();
    LoginPage loginPage = new LoginPage();

    private static final String VALID_USERNAME = "boaspessoal12345678@gmail.com";
    private static final String VALID_PASSWORD = "Manuel123";

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void loginAndOpenHistory() {
        open("https://papergames.io/en/match-history?gameType=Battleships");
        sleep(2000);

        if (historyPage.acceptCookiesButton.exists()) {
            executeJavaScript("arguments[0].click();", historyPage.acceptCookiesButton);
            sleep(500);
        }

        if (loginPage.openLoginModalButton.exists()) {
            executeJavaScript("arguments[0].click();", loginPage.openLoginModalButton);
            sleep(500);
            loginPage.usernameInput.shouldBe(visible).setValue(VALID_USERNAME);
            loginPage.passwordInput.shouldBe(visible).setValue(VALID_PASSWORD);
            executeJavaScript("arguments[0].click();", loginPage.submitButton);
            sleep(2000);

            if (historyPage.acceptCookiesButton.exists()) {
                executeJavaScript("arguments[0].click();", historyPage.acceptCookiesButton);
                sleep(500);
            }

            open("https://papergames.io/en/match-history?gameType=Battleships");
            sleep(2000);
        }
    }

    @Test
    public void historyTableIsVisible() {
        historyPage.historyTable.shouldBe(visible);
    }

    @Test
    public void historyTableHasCorrectHeaders() {
        historyPage.tableHeaderPlayers.shouldBe(visible);
        historyPage.tableHeaderResult.shouldBe(visible);
        historyPage.tableHeaderGame.shouldBe(visible);
        historyPage.tableHeaderDate.shouldBe(visible);
    }

    @Test
    public void historyHasAtLeastOneRow() {
        historyPage.historyTable.shouldBe(visible);
        assertFalse(historyPage.historyRows.isEmpty(),
                "O histórico deve ter pelo menos uma partida.");
    }

    @Test
    public void firstRowContainsBattleshipGame() {
        historyPage.firstRowGameType.shouldBe(visible);
        historyPage.firstRowGameType.shouldHave(text("Battleship"));
    }
}
