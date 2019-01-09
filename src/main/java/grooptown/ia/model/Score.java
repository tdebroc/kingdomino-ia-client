package grooptown.ia.model;

import lombok.Data;

/**
 * Created by thibautdebroca on 09/01/2019.
 */
@Data
public class Score {
    private int total;
    private int[] areaScores;
    private int centerBonus;
    private int completeBonus;
}
