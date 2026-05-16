package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class RankingPageTest {

    RankingPage rankingsPage = new RankingPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://papergames.io/en/");
        sleep(1000);
        if (rankingsPage.acceptCookiesButton.isDisplayed()) {
            executeJavaScript("arguments[0].click();", rankingsPage.acceptCookiesButton);
        }
    }

    @Test
    @DisplayName("US04 - Navegar até aos Rankings do Battleship e Verificar Tabela")
    public void testNavegarEVerificarRankings() {

        rankingsPage.battleshipGameCard.shouldBe(visible);
        executeJavaScript("arguments[0].click();", rankingsPage.battleshipGameCard);
        sleep(1500);


        rankingsPage.seeAllRankingsButton.shouldBe(visible);
        executeJavaScript("arguments[0].click();", rankingsPage.seeAllRankingsButton);
        sleep(2000); // Aguarda que a tabela de classificações carregue os dados da API


        String currentUrl = url();
        Assertions.assertTrue(currentUrl.contains("/en/t/") || currentUrl.contains("history"),
                "O URL atual não corresponde à página de classificações!");


        rankingsPage.rankingsTable.shouldBe(visible);
    }
}