package grooptown.ia.model;

import lombok.Data;

@Data
public class Kingdom {
    private Player player;
    private PlacedTiles[] placedTiles;
    private Score score;

}
