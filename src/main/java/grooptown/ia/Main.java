package grooptown.ia;

import grooptown.ia.model.Game;
import grooptown.ia.model.GameState;

import static grooptown.ia.SSLUtil.disableSSLValidation;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        disableSSLValidation();
        PlayerConnector.baseUrl = "https://domi-nation.grooptown.com";
        int playerCount = 3;
        int maxTurnToPlay = 20;

        PlayerConnector[] playerConnectors = new PlayerConnector[playerCount];

        Game newGame = PlayerConnector.createNewGame(playerCount);

        for (int i = 0; i < playerConnectors.length; i++) {
            playerConnectors[i] = new PlayerConnector(newGame.getUuid());
            playerConnectors[i].joinGame("Player" + i);
        }

        String gameStateAsString = PlayerConnector.getGameStateAsString(newGame.getUuid());
        System.out.println(gameStateAsString);

        GameState gameState = PlayerConnector.getGameState(newGame.getUuid());
        System.out.println(gameState);

        for (int i = 0; i < maxTurnToPlay; i++) {
            PlayerConnector playerConnector = getCurrentPlayerConnector(playerConnectors, gameState);
            playerConnector.playMove(0);
            gameState = PlayerConnector.getGameState(newGame.getUuid());
            System.out.println(gameState);
        }
    }

    private static PlayerConnector getCurrentPlayerConnector(PlayerConnector[] playerConnectors,
                                                             GameState gameState) {
        for (PlayerConnector playerConnector : playerConnectors) {
            if (gameState.getCurrentPlayer().getName().equals(playerConnector.getPlayer().getName())) {
                return playerConnector;
            }
        }
        return null;
    }

}
