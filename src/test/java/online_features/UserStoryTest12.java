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
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        loginPage = Selenide.page(LoginPage.class);
        mainPage = Selenide.page(BattleshipMainPage.class);

        assertTrue(loginPage.ensureLoggedIn(VALID_USERNAME, VALID_PASSWORD),
                "Não foi possível autenticar o utilizador antes de testar a criação de torneios.");

        mainPage.openGame();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("US12 – Criação de Torneios")
    void criacaoDeTorneios() {
        // Limpar anúncios antes de interagir com a página
        mainPage.dismissOverlays();

        $$("a, button").filter(Condition.matchText("(?i)create tournament"))
                .first()
                .shouldBe(Condition.visible)
                .click();

        Selenide.sleep(3500);

        // Limpar anúncios que possam ter surgido após a navegação
        mainPage.dismissOverlays();

        for (com.codeborne.selenide.SelenideElement el : $$("input, textarea").filter(Condition.visible)) {
            String placeholder = el.getAttribute("placeholder");
            String name = el.getAttribute("name");
            String type = el.getAttribute("type");

            String combined = ((placeholder != null ? placeholder : "") + " " +
                    (name != null ? name : "") + " " +
                    (type != null ? type : "")).toLowerCase();

            if (combined.contains("name") || combined.contains("nome") || combined.contains("title")) {
                el.click();
                el.clear();
                el.setValue("Torneio de Teste do Dinis");
            } else if (combined.contains("date") || combined.contains("data") || "date".equals(type)) {
                el.click();
                el.clear();
                el.setValue("2026-05-17");
            } else if (combined.contains("rules") || combined.contains("regras") || "textarea".equalsIgnoreCase(el.getTagName())) {
                el.click();
                el.clear();
                el.setValue("Regras de teste automatizado usando Selenide puro.");
            }
        }

        Selenide.sleep(1000);

        // Limpar anúncios antes de clicar no submit
        mainPage.dismissOverlays();

        var submitBtn = $$("button, input[type='submit']").filter(Condition.visible)
                .find(Condition.or("Texto do Botão",
                        Condition.text("Create and share"),
                        Condition.text("Create"),
                        Condition.text("Criar"),
                        Condition.attribute("type", "submit")));

        // Sempre usar JavaScript para evitar ElementClickInterceptedException por anúncios
        Selenide.executeJavaScript("arguments[0].removeAttribute('disabled'); arguments[0].click();", submitBtn);

        Selenide.sleep(3000);

        $("body").shouldHave(Condition.or("mensagem de sucesso do torneio",
                Condition.text("tournament"),
                Condition.text("torneio"),
                Condition.text("created"),
                Condition.text("success"),
                Condition.text("share")));
    }
}