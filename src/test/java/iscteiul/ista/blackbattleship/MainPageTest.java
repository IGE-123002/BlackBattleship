package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://www.jetbrains.com/");
        if ($("#onetrust-accept-btn-handler").is(visible)) {
            $("#onetrust-accept-btn-handler").click();
        }
    }

    @Test
    public void search() {
        executeJavaScript("arguments[0].click();", mainPage.menuButton);
        executeJavaScript("arguments[0].click();", mainPage.searchButton);
        // verifica que a URL mudou ou que apareceu algo de pesquisa
        $("[data-test='site-header-search-field'], input, [placeholder]").shouldBe(visible);

    }

    @Test
    public void toolsMenu() {
        executeJavaScript("arguments[0].click();", mainPage.menuButton);
        executeJavaScript("arguments[0].click();", mainPage.toolsMenu);
        mainPage.seeDeveloperToolsButton.shouldBe(visible);
    }

    @Test
    public void navigationToAllTools() {
        executeJavaScript("arguments[0].click();", mainPage.menuButton);
        executeJavaScript("arguments[0].click();", mainPage.toolsMenu);
        executeJavaScript("arguments[0].click();", mainPage.seeDeveloperToolsButton);

        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
    }

}
