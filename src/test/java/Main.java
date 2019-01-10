import grooptown.ia.PlayerConnector;
import grooptown.ia.model.AvailableMoves;
import grooptown.ia.model.Game;
import grooptown.ia.model.GameState;

import static grooptown.ia.SSLUtil.disableSSLValidation;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
public class Main {

    /**
     * Example on how to use the playerConnector.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // With JDK inferior to 8u101 you need to disable SSL validation.
        // disableSSLValidation();
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
            AvailableMoves availableMove = playerConnector.getAvailableMove();
            System.out.println("Availables Moves are: " + availableMove);
            gameState = PlayerConnector.getGameState(newGame.getUuid());
            System.out.println("Game State is " + gameState);
        }
    }


    /**
     * Gets the Player that should play the current Turn. If not found, return null.
     * @param playerConnectors
     * @param gameState
     * @return
     */
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
