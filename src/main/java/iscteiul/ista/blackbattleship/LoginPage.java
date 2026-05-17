package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;



// page_url = https://papergames.io/en/
public class LoginPage {


    public SelenideElement openLoginModalButton = Selenide.$x("//button[contains(@class,'btn') and (contains(text(),'Log in') or contains(text(),'Login'))]");


    public SelenideElement usernameInput = Selenide.$("input[type='email'], input[formcontrolname='username']");
    public SelenideElement passwordInput = Selenide.$("input[type='password']");


    public SelenideElement submitButton = Selenide.$("button[type='submit']");


    public SelenideElement errorMessage = Selenide.$x("//mat-error | //div[contains(@class,'alert-danger')] | //span[contains(@class,'error')]");


    public SelenideElement userAvatar = Selenide.$(".user-profile, button.mat-mdc-menu-trigger");
    public SelenideElement logoutButton = Selenide.$x("//span[text()='Logout']");


    public SelenideElement acceptCookiesButton = Selenide.$("button.fc-cta-consent");
}