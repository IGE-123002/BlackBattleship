package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;


// page_url = https://papergames.io/en/battleship
public class ChatPage {

    public SelenideElement playVsRobotButton = Selenide.$("app-juicy-button button.btn-light");

    public SelenideElement chatInput = Selenide.$("textarea[formcontrolname='message']");

    public SelenideElement sendMessageButton = Selenide.$("button[aria-label='Send message']");

    public SelenideElement lastChatMessage = Selenide.$("div.message-bubble");

    public SelenideElement acceptCookiesButton = Selenide.$("button.fc-cta-consent");

    public SelenideElement userAvatar = Selenide.$("button.mat-mdc-menu-trigger .user-profile");

    public SelenideElement logoutButton = Selenide.$x("//span[text()='Logout']");
}
