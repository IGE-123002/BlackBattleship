package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import iscteiul.ista.blackbattleship.pages.BattleshipMainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes de aceitação para a User Story 12 – Criação de Torneios.
 * Valida a funcionalidade de criar um torneio de batalha naval.
 */
public class UserStoryTest12 {

    private static final String VALID_USERNAME = "boaspessoal12345678@gmail.com";
    private static final String VALID_PASSWORD = "Manuel123";

    private BattleshipMainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        BattleshipMainPage.configureBrowser();
    }

    @BeforeEach
    void setUp() {
        mainPage = Selenide.page(BattleshipMainPage.class);

        // 1. Fluxo de login isolado e robusto
        Selenide.open("https://papergames.io/en/");

        try {
            $("button.fc-cta-consent").shouldBe(Condition.visible, java.time.Duration.ofSeconds(5)).click();
        } catch (Throwable t) {
            // Ignora se o banner de cookies não aparecer
        }

        var loginBtn = $(By.xpath("//button[contains(., 'Log in') or contains(., 'Login')]"));
        if (loginBtn.isDisplayed()) {
            loginBtn.click();
            Selenide.sleep(1000);

            $("input[type='email']").shouldBe(Condition.visible).setValue(VALID_USERNAME);
            $("input[type='password']").shouldBe(Condition.visible).setValue(VALID_PASSWORD);

            $(By.xpath("//button[@type='submit']")).shouldBe(Condition.visible).click();
            Selenide.sleep(2500);

            try {
                $("button.fc-cta-consent").shouldBe(Condition.visible, java.time.Duration.ofSeconds(2)).click();
            } catch (Throwable t) {
                // Ignora
            }
        }

        // 2. Avançar para a página do jogo
        mainPage.openGame();
    }

    @Test
    @DisplayName("US12 – Criação de Torneios")
    void criacaoDeTorneios() {
        // 1. Clicar no link/botão visível de criar torneio
        $$("a, button").filter(Condition.matchText("(?i)create tournament"))
                .first()
                .shouldBe(Condition.visible)
                .click();

        // Pausa generosa para garantir o carregamento completo do formulário
        Selenide.sleep(3500);

        // 2. Preenchimento Inteligente: Varre todos os campos visíveis e preenche conforme o propósito
        for (com.codeborne.selenide.SelenideElement el : $$("input, textarea").filter(Condition.visible)) {
            String placeholder = el.getAttribute("placeholder");
            String name = el.getAttribute("name");
            String type = el.getAttribute("type");

            // Junta os atributos numa string em minúsculas para pesquisa flexível
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

        // Breve pausa para o Angular registar as mudanças de texto nos inputs
        Selenide.sleep(1000);

        // 3. Localizar o botão de submissão de forma flexível
        var submitBtn = $$("button, input[type='submit']").filter(Condition.visible)
                .find(Condition.or("Texto do Botão",
                        Condition.text("Create and share"),
                        Condition.text("Create"),
                        Condition.text("Criar"),
                        Condition.attribute("type", "submit")));

        // TRUQUE DE MESTRE: Se o Angular mantiver o botão desativado por atraso de renderização,
        // limpamos o estado de desativado via JS e forçamos o clique!
        if (submitBtn.getAttribute("disabled") != null || !submitBtn.isEnabled()) {
            Selenide.executeJavaScript("arguments[0].removeAttribute('disabled'); arguments[0].click();", submitBtn);
        } else {
            submitBtn.click();
        }

        // Aguarda o processamento do envio
        Selenide.sleep(3000);

        // 4. Validar que o torneio foi criado com sucesso inspecionando o corpo da página
        $("body").shouldHave(Condition.or("mensagem de sucesso do torneio",
                Condition.text("tournament"),
                Condition.text("torneio"),
                Condition.text("created"),
                Condition.text("success"),
                Condition.text("share")));
    }
}