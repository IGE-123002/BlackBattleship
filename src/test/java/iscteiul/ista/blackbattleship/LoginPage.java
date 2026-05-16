package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://papergames.io/en/
public class LoginPage {


    public SelenideElement openLoginModalButton = $x("//button[contains(@class,'btn') and (contains(text(),'Log in') or contains(text(),'Login'))]");


    public SelenideElement usernameInput = $("input[type='email'], input[formcontrolname='username']");
    public SelenideElement passwordInput = $("input[type='password']");


    public SelenideElement submitButton = $("button[type='submit']");


    public SelenideElement errorMessage = $x("//mat-error | //div[contains(@class,'alert-danger')] | //span[contains(@class,'error')]");


    public SelenideElement userAvatar = $(".user-profile, button.mat-mdc-menu-trigger");
    public SelenideElement logoutButton = $x("//span[text()='Logout']");


    public SelenideElement acceptCookiesButton = $("button.fc-cta-consent");
}