package iscteiul.ista.blackbattleship.userstories;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object para a página de histórico de partidas do PaperGames.
 * Contém os localizadores CSS/XPath dos elementos do histórico.
 */
public class MatchHistoryPage {

    public SelenideElement historySection = $("[class*='history'], [class*='matches']");

    public ElementsCollection matchEntries = $$("[class*='match-item'], [class*='history-item'], tr[class*='match']");

    public SelenideElement profileLink = $x("//a[contains(@href, 'profile') or contains(., 'Profile')]");
}
