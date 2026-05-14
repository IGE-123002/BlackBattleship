package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://www.jetbrains.com/");

        // 1. O GRANDE TRUQUE: Esperar 1.5 segundos para dar tempo ao banner de aparecer!
        sleep(1500);

        // 2. Tenta encontrar qualquer botão que diga Accept ou Accept All
        if ($x("//button[contains(., 'Accept')]").isDisplayed()) {
            $x("//button[contains(., 'Accept')]").click();
            // Dá meio segundo para a animação do banner a desaparecer terminar
            sleep(500);
        }
    }

    @Test
    public void search() {
        mainPage.searchButton.shouldBe(Condition.interactable).click(ClickOptions.usingJavaScript());

        // JS Click na caixa para escapar a qualquer lixo visual que esteja por cima
        mainPage.searchInput.shouldBe(Condition.visible, Duration.ofSeconds(5))
                .click(ClickOptions.usingJavaScript());

        mainPage.searchInput.setValue("Selenium");

        mainPage.searchInput.shouldHave(Condition.value("Selenium"), Duration.ofSeconds(5));
    }

    @Test
    public void toolsMenu() {
        mainPage.developerToolsTexts.findBy(Condition.interactable).click(ClickOptions.usingJavaScript());
        $$x("//*[contains(text(), 'IDEs')]").findBy(Condition.visible).shouldBe(Condition.visible);
    }

    @Test
    public void navigationToAllTools() {
        mainPage.developerToolsTexts.findBy(Condition.interactable).click(ClickOptions.usingJavaScript());

        mainPage.findYourToolsLinks.findBy(Condition.interactable)
                .click(ClickOptions.usingJavaScript());

        String title = title();
        assertTrue(title != null && (title.contains("Tools") || title.contains("Products")),
                "O título não corresponde. Título atual: " + title);
    }
}