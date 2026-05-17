package iscteiul.ista.blackbattleship.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object para https://papergames.io/en/battleship.
 *
 * <p>Cobre a barra de navegação (botão de perfil) e a secção de regras do jogo.</p>
 */
public class BattleshipHomePage {

    /**
     * Botão de perfil na barra de navegação superior.
     * Identifica-se por ser o único {@code button} com classe {@code mdc-button}
     * e {@code mat-mdc-menu-trigger} (os restantes botões de menu usam {@code mdc-icon-button}).
     */
    public final SelenideElement profileMenuButton = $("button.mdc-button.mat-mdc-menu-trigger");

    /**
     * Secção de regras e instruções do jogo, localizada acima do footer.
     */
    public final SelenideElement rulesSection = $("section.section");

    /**
     * Primeiro cabeçalho H2 dentro da secção de regras.
     * Texto esperado: "Rules of Battleship game online".
     */
    public final SelenideElement rulesHeading = $("section.section h2");

    /**
     * Elemento footer da página.
     */
    public final SelenideElement footer = $("footer");

    /**
     * Abre o menu dropdown do perfil de utilizador.
     */
    public void openProfileMenu() {
        profileMenuButton.click();
    }

    /**
     * Navega para a página "My Account" através do menu de perfil.
     *
     * @return instância de {@link AccountPage} após a navegação
     */
    public AccountPage goToMyAccount() {
        openProfileMenu();
        $("a.mat-mdc-menu-item[href='/en/account']").click();
        return new AccountPage();
    }
}
