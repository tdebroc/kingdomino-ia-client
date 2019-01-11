package grooptown.ia.model;

import lombok.Data;

@Data
class Move {
    private int number;
    private Domino chosenDomino;
    private PlacedDomino placedDomino;
}
