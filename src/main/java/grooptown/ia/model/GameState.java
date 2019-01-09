package grooptown.ia.model;

import lombok.Data;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
@Data
public class GameState {
    String uuid;
    String created;
    String updated;
    Kingdom[] kingdoms;
    Domino[] usedDominoes;
    CurrentDraft currentDraft;
    CurrentDraft previousDraft;
    Player currentPlayer;
    boolean gameOver;
    int turn;
}
