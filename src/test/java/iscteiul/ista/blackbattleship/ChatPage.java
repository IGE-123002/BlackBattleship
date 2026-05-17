package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://papergames.io/en/battleship
public class ChatPage {

    public SelenideElement playVsRobotButton = $("app-juicy-button button.btn-light");

    public SelenideElement chatInput = $("textarea[formcontrolname='message']");

    public SelenideElement sendMessageButton = $("button[aria-label='Send message']");

    public SelenideElement lastChatMessage = $("div.message-bubble");

    public SelenideElement acceptCookiesButton = $("button.fc-cta-consent");

    public SelenideElement userAvatar = $("button.mat-mdc-menu-trigger .user-profile");

    public SelenideElement logoutButton = $x("//span[text()='Logout']");
}
