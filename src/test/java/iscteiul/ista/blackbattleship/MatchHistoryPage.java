package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// page_url = https://papergames.io/en/match-history?gameType=Battleships
public class MatchHistoryPage {

    public SelenideElement acceptCookiesButton = $("button.fc-cta-consent");

    public SelenideElement historyTable = $("table.mat-mdc-table");

    public SelenideElement tableHeaderPlayers = $("th.mat-column-players");
    public SelenideElement tableHeaderResult  = $("th.mat-column-result");
    public SelenideElement tableHeaderGame    = $("th.mat-column-gameType");
    public SelenideElement tableHeaderDate    = $("th.mat-column-createdAt");

    public ElementsCollection historyRows = $$("tr.mat-mdc-row");

    public SelenideElement firstRow = $("tr.mat-mdc-row");

    public SelenideElement firstRowPlayers = $("tr.mat-mdc-row td.mat-column-players");

    public SelenideElement firstRowResult = $("tr.mat-mdc-row td.mat-column-result");

    public SelenideElement firstRowGameType = $("tr.mat-mdc-row td.mat-column-gameType");

    public SelenideElement firstRowDate = $("tr.mat-mdc-row td.mat-column-createdAt");
}
