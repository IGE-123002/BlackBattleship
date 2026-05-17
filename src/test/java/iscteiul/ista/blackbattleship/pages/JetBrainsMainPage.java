package iscteiul.ista.blackbattleship.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class JetBrainsMainPage {
    public SelenideElement acceptCookiesButton = Selenide.$x("//button[contains(., 'Accept')]");

    public SelenideElement searchButton = Selenide.$("[data-test='site-header-search-action']");
    public SelenideElement searchInput = Selenide.$("[data-test-id='search-input']");

    public ElementsCollection developerToolsTexts = Selenide.$$x("//*[contains(text(), 'Developer Tools')]");

    // NOVO: Muito mais simples. Encontra todos os links que vão ter à página de produtos!
    public ElementsCollection findYourToolsLinks = Selenide.$$("a[href*='/products']");
}
