import grooptown.ia.PlayerConnector;
import grooptown.ia.model.GameState;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

/**
 * Connects a Player to a game.
 * Created by thibautdebroca on 09/01/2019.
 */
public class OnePlayerAloneTest {

    public static void main(String[] args) throws Exception {
        // With JDK inferior to 8u101 you need to disable SSL validation.
        // disableSSLValidation();
        PlayerConnector.baseUrl = "https://domi-nation.grooptown.com";

        int maxTurnToPlay = 30;
        String gameUUID = "c4a8956b-e24f-4a77-9195-90b4dbc9b0fc";
        String playerName = "Player-" + UUID.randomUUID();

        PlayerConnector playerConnector = new PlayerConnector(gameUUID);
        playerConnector.joinGame(playerName);


        while (true) {
            waitUntilItsMyTurn(playerConnector, gameUUID);

            GameState gameState = PlayerConnector.getGameState(gameUUID);
            System.out.println(gameState);

            System.out.println(playerName + " is playing a Move.");
            playerConnector.playMove(0);
        }

    }


    public static void waitUntilItsMyTurn(PlayerConnector playerConnector, String gameUUID) throws InterruptedException {
        while (true) {
            try {
                GameState gameState = PlayerConnector.getGameState(gameUUID);
                if (gameState.getCurrentPlayer().getName().equals(playerConnector.getPlayer().getName())) {
                    return;
                }
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                    System.out.println("Game has not started yet.");
                }
            }
            Thread.sleep(500);
        }
    }


}
