package iscteiul.ista.blackbattleship.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
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

        // Opções do Chrome para reduzir deteção de automação (reCAPTCHA)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        Configuration.browserCapabilities = options;

        // Ativação do Allure Framework para os relatórios da Parte 2
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    public void openGame() {
        Selenide.open(BASE_URL);

        // Dar tempo ao framework da página para renderizar os botões do jogo
        Selenide.sleep(2500);

        // Descartar banner de cookies e anúncios intersticiais do Google
        dismissOverlays();
    }

    /**
     * Remove todos os overlays que possam bloquear a interação:
     * banner de cookies (FundingChoices) e anúncios intersticiais do Google.
     * Executa tudo via JavaScript direto para evitar ElementNotInteractableException.
     */
    public void dismissOverlays() {
        // 1) Descartar o banner de cookies
        Selenide.executeJavaScript(
                "var btn = document.querySelector('button.fc-cta-consent');" +
                "if (btn && btn.offsetParent !== null) { btn.click(); }" +
                "var all = document.querySelectorAll('button');" +
                "for (var b of all) {" +
                "  var txt = b.textContent.toLowerCase();" +
                "  if ((txt.includes('consent') || txt.includes('accept') || txt.includes('agree'))" +
                "      && b.offsetParent !== null) { b.click(); break; }" +
                "}"
        );

        // 2) Remover iframes de anúncios intersticiais do Google que cobrem toda a página
        Selenide.executeJavaScript(
                "document.querySelectorAll('iframe[id*=\"google_ads\"], iframe[id*=\"aswift\"]').forEach(function(f){ f.remove(); });" +
                "document.querySelectorAll('div[id*=\"google_ads\"], div[id*=\"ad-slot\"], ins.adsbygoogle').forEach(function(d){ d.remove(); });" +
                "document.querySelectorAll('div').forEach(function(d){" +
                "  var s = window.getComputedStyle(d);" +
                "  if (s.position === 'fixed' && s.zIndex > 999 && d.querySelector('iframe')) { d.remove(); }" +
                "});"
        );

        Selenide.sleep(500);
    }
}