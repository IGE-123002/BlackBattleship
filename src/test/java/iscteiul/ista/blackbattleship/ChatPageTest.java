package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ChatPageTest {

    ChatPage chatPage = new ChatPage();
    LoginPage loginPage = new LoginPage();

    private static final String VALID_USERNAME = "boaspessoal12345678@gmail.com";
    private static final String VALID_PASSWORD = "Manuel123";
    private static final String TEST_MESSAGE = "Boa sorte!";

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void loginAndStartGame() {
        open("https://papergames.io/en/battleship");
        sleep(2000);

        if (chatPage.acceptCookiesButton.exists()) {
            executeJavaScript("arguments[0].click();", chatPage.acceptCookiesButton);
            sleep(500);
        }

        if (loginPage.openLoginModalButton.exists()) {
            executeJavaScript("arguments[0].click();", loginPage.openLoginModalButton);
            sleep(500);
            loginPage.usernameInput.shouldBe(visible).setValue(VALID_USERNAME);
            loginPage.passwordInput.shouldBe(visible).setValue(VALID_PASSWORD);
            executeJavaScript("arguments[0].click();", loginPage.submitButton);
            sleep(2000);

            if (chatPage.acceptCookiesButton.exists()) {
                executeJavaScript("arguments[0].click();", chatPage.acceptCookiesButton);
                sleep(500);
            }
        }

        chatPage.playVsRobotButton.shouldBe(visible).click();
        sleep(1000);

        chatPage.chatInput.shouldBe(visible);
    }
    @AfterEach
    public void logoutAfterTest() {
        open("https://papergames.io/en/");
        sleep(1000);

        if (chatPage.userAvatar.exists()) {
            executeJavaScript("arguments[0].click();", chatPage.userAvatar);
            sleep(600);
            if (chatPage.logoutButton.exists()) {
                executeJavaScript("arguments[0].click();", chatPage.logoutButton);
                sleep(1000);
            }
        }
    }

    @Test
    public void chatInputIsVisible() {
        chatPage.chatInput.shouldBe(visible);
    }

    @Test
    public void sendButtonDisabledWhenEmpty() {
        chatPage.chatInput.shouldBe(visible);
        chatPage.sendMessageButton.shouldBe(disabled);
    }

    @Test
    public void sendButtonEnabledAfterTyping() {
        chatPage.chatInput.shouldBe(visible).setValue(TEST_MESSAGE);
        chatPage.sendMessageButton.shouldNotBe(disabled);
    }

    @Test
    public void sendMessage() {
        chatPage.chatInput.shouldBe(visible).setValue(TEST_MESSAGE);
        executeJavaScript("arguments[0].click();", chatPage.sendMessageButton);
        sleep(1000);

        chatPage.chatInput.shouldBe(empty);
        chatPage.lastChatMessage.shouldBe(visible);
        chatPage.lastChatMessage.shouldHave(text(TEST_MESSAGE));
    }
}