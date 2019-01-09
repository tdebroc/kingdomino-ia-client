package grooptown.ia.model;

import lombok.Data;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
@Data
public class Kingdom {
    private Player player;
    private PlacedTiles[] placedTiles;
    private Score score;

}
