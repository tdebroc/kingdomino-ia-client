package grooptown.ia.model;

import lombok.Data;

@Data
public class PlacedDomino {
    private Domino domino;
    private Position tile1Position;
    private Position tile2Position;
}
