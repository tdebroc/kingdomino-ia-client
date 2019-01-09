package grooptown.ia;

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

    private static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

    public static Game createNewGame(int playerCount) {
        Game result = restTemplate.postForObject(
                baseUrl + "/new-games/?playerCount=" + playerCount,
                null, Game.class
        );
        return result;
    }

    public void joinGame(String playerName) {
        player = restTemplate.postForObject(
                baseUrl + "/new-games/" + gameId + "/join/" + playerName,
                null, Player.class
        );
    }

    public static String getGameStateAsString(String gameId) {
        String gameState = restTemplate.getForEntity(
                baseUrl + "/games/" + gameId,
                String.class
        ).getBody();
        return gameState;
    }

    public static GameState getGameState(String gameId) {
        GameState gameState = restTemplate.getForEntity(
                baseUrl + "/games/" + gameId,
                GameState.class
        ).getBody();
        return gameState;
    }

    public GameState playMove(int moveNumber) {
        System.out.println("Playing Move " + moveNumber + " for player " + player.getUuid());
        GameState gameState = restTemplate.postForEntity(
                baseUrl + "/games/" + gameId + "/players/" + player.getUuid() + "/moves/" + moveNumber,
                null,
                GameState.class
        ).getBody();
        return gameState;
    }

    public String playMoveAsString(int moveNumber) {
        System.out.println("Playing Move " + moveNumber + " for player " + player.getUuid());
        String gameState = restTemplate.postForEntity(
                baseUrl + "/games/" + gameId + "/players/" + player.getUuid() + "/moves/" + moveNumber,
                null,
                String.class
        ).getBody();
        return gameState;
    }


    public static void getAvailableMove(String gameId) {
        // TODO: To implements;
    }

}
