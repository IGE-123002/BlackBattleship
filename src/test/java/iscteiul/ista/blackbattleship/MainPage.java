package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public SelenideElement acceptCookiesButton = $x("//button[contains(., 'Accept')]");

    public SelenideElement searchButton = $("[data-test='site-header-search-action']");
    public SelenideElement searchInput = $("[data-test-id='search-input']");

    public ElementsCollection developerToolsTexts = $$x("//*[contains(text(), 'Developer Tools')]");

    // NOVO: Muito mais simples. Encontra todos os links que vão ter à página de produtos!
    public ElementsCollection findYourToolsLinks = $$("a[href*='/products']");
}