package grooptown.ia.model;

import lombok.Data;

@Data
public class Move {
    private int number;
    private Domino chosenDomino;
    private PlacedDomino placedDomino;
}
