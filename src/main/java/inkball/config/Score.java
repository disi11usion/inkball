package inkball.config;

import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private Integer grey;
    private Integer green;
    private Integer blue;
    private Integer yellow;
    private Integer orange;
    private double modifier = 1;
    private List<Double> scoreList = new ArrayList<>();

    public Score(JSONObject scoreJson) {
        this.grey = scoreJson.getInt("grey");
        this.green = scoreJson.getInt("green");
        this.blue = scoreJson.getInt("blue");
        this.yellow = scoreJson.getInt("yellow");
        this.orange = scoreJson.getInt("orange");
        scoreList.add(grey.doubleValue());
        scoreList.add(orange.doubleValue());
        scoreList.add(blue.doubleValue());
        scoreList.add(green.doubleValue());
        scoreList.add(yellow.doubleValue());
    }

    public Integer getGrey() {
        return grey;
    }

    public void setModifier(double modifier) {
        this.modifier = modifier;
    }

    public Integer getGreen() {
        return green;
    }

    public Integer getBlue() {
        return blue;
    }

    public Integer getYellow() {
        return yellow;
    }

    public Integer getOrange() {
        return orange;
    }


//    public Double getGreyAfterMod() {
//        return grey * modifier;
//    }
//
//    public Double getGreenAfterMod() {
//        return green * modifier;
//    }
//
//    public Double getBlueAfterMod() {
//        return blue * modifier;
//    }
//
//    public Double getYellowAfterMod() {
//        return yellow * modifier;
//    }
//
//    public Double getOrangeAfterMod() {
//        return orange * modifier;
//    }

    public List<Double> getScoreAfterMod() {
        ArrayList<Double> scoreAfterMod = new ArrayList<>(scoreList);
        for (double i : scoreAfterMod) {
            i *= modifier;
        }
        return scoreAfterMod;
    }
}
