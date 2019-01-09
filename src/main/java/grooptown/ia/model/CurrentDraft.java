package grooptown.ia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
@Data
public class CurrentDraft {
    private DominoesElement[] dominoes;
}
