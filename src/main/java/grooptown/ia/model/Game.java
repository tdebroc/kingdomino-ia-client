package grooptown.ia.model;

import lombok.Data;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
@Data
public class Game {
    private String uuid;
    private String created;
    private String updated;
    private int numberOfPlayers;
    private Player[] joinedPlayers;
}
