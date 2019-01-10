package grooptown.ia;

import grooptown.ia.model.AvailableMoves;
import grooptown.ia.model.Game;
import grooptown.ia.model.GameState;
import grooptown.ia.model.Player;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
@Data
public class PlayerConnector {

    private String gameId;

    public Player player;

    public static String baseUrl = "https://domi-nation.grooptown.com";

    private static RestTemplate restTemplate = getRestTemplate();

    public PlayerConnector(String gameId) {
        this.gameId = gameId;
    }

    /**
     * Constructor for RestTemplate.
     * @return
     */
    private static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

    /**
     * Creates a Game on the server.
     * @param playerCount
     * @return
     */
    public static Game createNewGame(int playerCount) {
        return restTemplate.postForObject(
                baseUrl + "/new-games/?playerCount=" + playerCount,
                null, Game.class
        );
    }

    /**
     * Make one player joining the game. PlayerConnector will store the secret uuid of the player.
     * @param playerName
     */
    public void joinGame(String playerName) {
        player = restTemplate.postForObject(
                baseUrl + "/new-games/" + gameId + "/join/" + playerName,
                null, Player.class
        );
    }

    /**
     * Gets game state as a String.
     * @param gameId
     * @return
     */
    public static String getGameStateAsString(String gameId) {
        return restTemplate.getForEntity(
                baseUrl + "/games/" + gameId,
                String.class
        ).getBody();
    }


    /**
     * Gets Game State as a Java Object GameState.
     * @param gameId
     * @return
     */
    public static GameState getGameState(String gameId) {
        return restTemplate.getForEntity(
                baseUrl + "/games/" + gameId,
                GameState.class
        ).getBody();
    }


    /**
     * The players will play move number "moveNumber".
     * @param moveNumber
     * @return
     */
    public GameState playMove(int moveNumber) {
        System.out.println("Playing Move " + moveNumber + " for player " + player.getUuid());
        GameState gameState = restTemplate.postForEntity(
                baseUrl + "/games/" + gameId + "/players/" + player.getUuid() + "/moves/" + moveNumber,
                null,
                GameState.class
        ).getBody();
        return gameState;
    }

    /**
     * Gets Available Moves in the current turn.
     * @return
     */
    public AvailableMoves getAvailableMove() {
        return restTemplate.getForEntity(
                baseUrl + "/games/" + gameId + "/available-moves",
                AvailableMoves.class
        ).getBody();
    }

}
