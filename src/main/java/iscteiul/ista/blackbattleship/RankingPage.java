package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;



public class RankingPage {

    public SelenideElement acceptCookiesButton = Selenide.$("button.fc-cta-consent");


    public SelenideElement battleshipGameCard = Selenide.$x("//a[@href='/en/battleship']");


    public SelenideElement seeAllRankingsButton = Selenide.$x("//a[contains(@class,'btn-link') and contains(text(),'See all')]");


    public SelenideElement rankingsTable = Selenide.$("table, .table, .leaderboard-table");


    public SelenideElement tableHeader = Selenide.$x("//th[contains(text(),'User') or contains(text(),'Player') or contains(text(),'Rating')]");
}
