    package iscteiul.ista.blackbattleship;

    import com.codeborne.selenide.SelenideElement;

    import static com.codeborne.selenide.Selenide.$;
    import static com.codeborne.selenide.Selenide.$x;

    // page_url = https://www.jetbrains.com/
    public class MainPage {
        public SelenideElement menuButton = $("[data-test='site-header-open-mobile-main-menu-action']");
        public SelenideElement searchButton = $("[data-test='site-header-mobile-search-action']");
        public SelenideElement toolsMenu = $("[data-test='mobile-main-menu-item-action']");
        public SelenideElement seeDeveloperToolsButton = $x("//nav//a[@href='/products/'] | //div[contains(@class,'menu')]//a[@href='/products/']");
    }

