package grooptown.ia.model;

import lombok.Data;

@Data
class Kingdom {
    private Player player;
    private PlacedTiles[] placedTiles;
    private Score score;

}
