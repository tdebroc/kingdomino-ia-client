package grooptown.ia.model;

import lombok.Data;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
@Data
public class GameState {
    private String uuid;
    private String created;
    private String updated;
    private Kingdom[] kingdoms;
    private Domino[] usedDominoes;
    private CurrentDraft currentDraft;
    private CurrentDraft previousDraft;
    private Player currentPlayer;
    private boolean gameOver;
    private int turn;
}
