package grooptown.ia.model;

import lombok.Data;

@Data
public class Game {
    private String uuid;
    private String created;
    private String updated;
    private int numberOfPlayers;
    private Player[] joinedPlayers;
}
