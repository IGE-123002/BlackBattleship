package iscteiul.ista.blackbattleship.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object para https://papergames.io/en/account.
 *
 * <p>Expõe os elementos do formulário de conta e ações para atualizar o perfil.</p>
 */
public class AccountPage {

    /**
     * Campo de texto "Display Name".
     * Identificado pelo atributo Angular {@code formcontrolname="fullName"}.
     */
    public final SelenideElement displayNameInput = $("input[formcontrolname='fullName']");

    /**
     * Botão "Update" para submeter o formulário de conta.
     */
    public final SelenideElement updateButton = $("button.btn.btn-secondary[type='submit']");

    /**
     * Toast de notificação que aparece após uma atualização bem-sucedida.
     * Texto esperado: "Profile updated successfully."
     */
    public final SelenideElement successToast = $(".toast-title");

    /**
     * Limpa o campo "Display Name" e insere um novo valor.
     *
     * @param name novo nome a definir
     */
    public void setDisplayName(String name) {
        displayNameInput.clear();
        displayNameInput.setValue(name);
    }

    /**
     * Clica no botão "Update" para guardar as alterações.
     */
    public void clickUpdate() {
        updateButton.click();
    }

    /**
     * Devolve o valor atual do campo "Display Name".
     *
     * @return nome de utilizador atual
     */
    public String getDisplayName() {
        return displayNameInput.getValue();
    }
}
