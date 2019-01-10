package grooptown.ia.model;

import lombok.Data;

/**
 * Created by thibautdebroca on 10/01/2019.
 */
@Data
public class PlacedDomino {
    private Domino domino;
    private Position tile1Position;
    private Position tile2Position;
}
