package iscteiul.ista.blackbattleship.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Page Object para o ecrã de jogo ativo em https://papergames.io/en/r/{code}.
 *
 * <p>O tabuleiro é composto por dois {@code table.table-board}:</p>
 * <ul>
 *   <li>Índice 0 – grid do jogador (células com classe {@code undefined}).</li>
 *   <li>Índice 1 – grid do adversário (células com classe {@code null}, alvo de disparos).</li>
 * </ul>
 *
 * <p>Após um disparo, o {@code svg.intersection} da célula visada ganha a classe
 * {@code no-hit} (erro) ou {@code hit} (acerto).</p>
 */
public class GamePage {

    /** Coleção de ambos os grids (player + adversário). */
    public final ElementsCollection gridTables = $$("table.table-board");

    /**
     * Botão "Abort game" presente no diálogo de confirmação que surge
     * ao tentar sair de um jogo ativo com o botão de retroceder.
     */
    public final SelenideElement abortButton = $$("button").findBy(text("Abort game"));

    /**
     * Devolve o grid do adversário (segundo {@code table.table-board}),
     * aguardando até 10 segundos pela sua presença no DOM.
     *
     * @return elemento do grid adversário
     */
    public SelenideElement getOpponentGrid() {
        gridTables.shouldHave(sizeGreaterThan(1), Duration.ofSeconds(10));
        return gridTables.get(1);
    }

    /**
     * Devolve a primeira célula ({@code td}) do grid adversário.
     *
     * @return primeira célula do grid adversário
     */
    public SelenideElement getFirstOpponentCell() {
        return getOpponentGrid().$("td");
    }

    /**
     * Devolve o elemento SVG ({@code svg.intersection}) dentro da primeira
     * célula do grid adversário. É este elemento que muda de classe após o disparo.
     *
     * @return SVG da primeira célula adversária
     */
    public SelenideElement getFirstOpponentCellSvg() {
        return getFirstOpponentCell().$("svg.intersection");
    }

    /**
     * Executa um disparo clicando na primeira célula disponível do grid adversário.
     */
    public void fireAtFirstCell() {
        getFirstOpponentCell().click();
    }

    /**
     * Aguarda e confirma o diálogo de abort, clicando em "Abort game".
     */
    public void confirmAbort() {
        abortButton.shouldBe(visible, Duration.ofSeconds(5));
        abortButton.click();
    }
}
