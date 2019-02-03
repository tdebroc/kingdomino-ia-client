package grooptown.ia.model;

import lombok.Data;

@Data
public class Score {
    private int total;
    private int[] areaScores;
    private int centerBonus;
    private int completeBonus;
}
