package inkball.config;

import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private double modifier = 1;
    private final List<Double> scoreList = new ArrayList<>();

    public Score(JSONObject scoreJson) {
        int grey = scoreJson.getInt("grey");
        int green = scoreJson.getInt("green");
        int blue = scoreJson.getInt("blue");
        int yellow = scoreJson.getInt("yellow");
        int orange = scoreJson.getInt("orange");
        scoreList.add((double) grey);
        scoreList.add((double) orange);
        scoreList.add((double) blue);
        scoreList.add((double) green);
        scoreList.add((double) yellow);
    }

    public void setModifier(double modifier) {
        this.modifier = modifier;
    }


    public List<Double> getScoreAfterMod() {
        ArrayList<Double> scoreAfterMod = new ArrayList<>();
        for (double score : scoreList) {
            scoreAfterMod.add(score * modifier);
        }

        return scoreAfterMod;
    }
}
