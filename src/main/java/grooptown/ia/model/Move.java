package grooptown.ia.model;

import lombok.Data;

/**
 * Created by thibautdebroca on 10/01/2019.
 */
@Data
public class Move {
    private int number;
    private Domino chosenDomino;
    private PlacedDomino placedDomino;
}
