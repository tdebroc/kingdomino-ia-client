package grooptown.ia;

import grooptown.ia.model.Game;

import static grooptown.ia.SSLUtil.disableSSLValidation;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        disableSSLValidation();
        PlayerConnector.baseUrl = "https://domi-nation.grooptown.com";

        Game newGame = PlayerConnector.createNewGame(2);

        PlayerConnector playerConnector = new PlayerConnector(newGame.getUuid());
        playerConnector.joinGame("Bob");

        PlayerConnector playerConnectorAnnna = new PlayerConnector(newGame.getUuid());
        playerConnectorAnnna.joinGame("Anna");


        PlayerConnector.getGameStateAsString(newGame.getUuid());
        PlayerConnector.getGameState(newGame.getUuid());
    }

}
