package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;



// page_url = https://papergames.io/en/match-history?gameType=Battleships
public class MatchHistoryPage {

    public SelenideElement acceptCookiesButton = Selenide.$("button.fc-cta-consent");

    public SelenideElement historyTable = Selenide.$("table.mat-mdc-table");

    public SelenideElement tableHeaderPlayers = Selenide.$("th.mat-column-players");
    public SelenideElement tableHeaderResult  = Selenide.$("th.mat-column-result");
    public SelenideElement tableHeaderGame    = Selenide.$("th.mat-column-gameType");
    public SelenideElement tableHeaderDate    = Selenide.$("th.mat-column-createdAt");

    public ElementsCollection historyRows = Selenide.$$("tr.mat-mdc-row");

    public SelenideElement firstRow = Selenide.$("tr.mat-mdc-row");

    public SelenideElement firstRowPlayers = Selenide.$("tr.mat-mdc-row td.mat-column-players");

    public SelenideElement firstRowResult = Selenide.$("tr.mat-mdc-row td.mat-column-result");

    public SelenideElement firstRowGameType = Selenide.$("tr.mat-mdc-row td.mat-column-gameType");

    public SelenideElement firstRowDate = Selenide.$("tr.mat-mdc-row td.mat-column-createdAt");
}
