package grooptown.ia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
@Data
public class Domino {
    private int number;
    private Tile tile1;
    private Tile tile2;
}
