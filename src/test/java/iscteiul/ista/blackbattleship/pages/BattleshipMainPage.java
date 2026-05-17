package iscteiul.ista.blackbattleship.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object unificado para a página principal do jogo usando Page Factory.
 */
public class BattleshipMainPage {

    private static final String BASE_URL = "https://papergames.io/en/battleship";

    // Elementos de Cookies e Consentimento
    @FindBy(css = "button.fc-cta-consent")
    public SelenideElement cookieConsentBanner;

    // XPath restrito: agora procura por "play with", "invite" ou "jogar com",
    // evitando clicar na aba geral de "friends"
    @FindBy(xpath = "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'play with') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invite') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'jogar com') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'convidar')] | //a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'play with') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invite') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'jogar com') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'convidar')]")
    public SelenideElement inviteFriendButton;

    // Mapeia todas as potenciais caixas de texto ou links onde o convite possa aparecer
    @FindBy(css = "input, textarea, a")
    public com.codeborne.selenide.ElementsCollection allShareElements;

    // Método que encapsula a lógica de negócio: varrer os elementos para encontrar o link gerado
    public String getGeneratedInviteLink() {
        for (com.codeborne.selenide.SelenideElement el : allShareElements) {
            if (!el.exists() || !el.isDisplayed()) continue;

            // Verifica se o texto dinâmico (JavaScript) contém o link
            String val = el.getValue();
            if (val != null && (val.contains("http") || val.contains("papergames"))) {
                return val;
            }

            // Alternativa: Se o link foi gerado numa tag <a>
            String href = el.getAttribute("href");
            if (href != null && (href.contains("http") || href.contains("papergames"))) {
                return href;
            }
        }
        return null;
    }

    // US08 – Elementos para Matchmaking Aleatório
    // XPath avançado que procura por "play online", "online" ou "jogar online" em <a> ou <button>
    @FindBy(xpath = "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'play online') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'jogar online') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'online')] | //a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'play online') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'jogar online') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'online')]")
    public SelenideElement playOnlineButton;

    // US10 – Elementos para Armas Especiais
    @FindBy(xpath = "//*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'special weapons') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'armas especiais') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'weapons')]")
    public SelenideElement specialWeaponsMenu;

    @FindBy(xpath = "//*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'big missile') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'míssil grande') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'missile')]")
    public SelenideElement bigMissileOption;

    // US12 – Elementos para Criação de Torneios (Atualizados preventivamente!)
    @FindBy(xpath = "//*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'tournament') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'torneio')]")
    public SelenideElement tournamentMenu;

    @FindBy(css = "input[name*='name'], input[placeholder*='name'], input[placeholder*='nome']")
    public SelenideElement tournamentNameInput;

    @FindBy(css = "input[type='date'], input[name*='date'], input[placeholder*='data']")
    public SelenideElement tournamentDateInput;

    @FindBy(css = "textarea[name*='rules'], input[name*='rules']")
    public SelenideElement tournamentRulesInput;

    @FindBy(xpath = "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'create') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'criar') or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'submit')]")
    public SelenideElement createTournamentSubmitButton;

    public static void configureBrowser() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 10000;
        // Ativação do Allure Framework para os relatórios da Parte 2
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    public void openGame() {
        Selenide.open(BASE_URL);

        // Dar tempo ao framework da página para renderizar os botões do jogo
        Selenide.sleep(2500);

        if (cookieConsentBanner.isDisplayed()) {
            cookieConsentBanner.click();
        }
    }
}