package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RankingPage {

    public SelenideElement acceptCookiesButton = $("button.fc-cta-consent");


    public SelenideElement battleshipGameCard = $x("//a[@href='/en/battleship']");


    public SelenideElement seeAllRankingsButton = $x("//a[contains(@class,'btn-link') and contains(text(),'See all')]");


    public SelenideElement rankingsTable = $("table, .table, .leaderboard-table");


    public SelenideElement tableHeader = $x("//th[contains(text(),'User') or contains(text(),'Player') or contains(text(),'Rating')]");
}
