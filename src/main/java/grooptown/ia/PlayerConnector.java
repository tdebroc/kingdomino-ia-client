package grooptown.ia;

import grooptown.ia.model.Game;
import grooptown.ia.model.GameState;
import grooptown.ia.model.Player;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static grooptown.ia.SSLUtil.disableSSLValidation;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
public class PlayerConnector {

    private String gameId;

    private Player player;

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
        System.out.println(gameState);
        return gameState;
    }

    public static GameState getGameState(String gameId) {
        GameState gameState = restTemplate.getForEntity(
                baseUrl + "/games/" + gameId,
                GameState.class
        ).getBody();
        System.out.println(gameState);
        return gameState;
    }


    public static void getAvailableMove(String gameId) {
        // TODO: To implements;
    }

}
