package inkball.config;

import processing.data.JSONObject;

public class Score {
    private Integer grey;
    private Integer green;
    private Integer blue;
    private Integer yellow;
    private Integer orange;

    public Score(JSONObject scoreJson) {
        this.grey = scoreJson.getInt("grey");
        this.green = scoreJson.getInt("green");
        this.blue = scoreJson.getInt("blue");
        this.yellow = scoreJson.getInt("yellow");
        this.orange = scoreJson.getInt("orange");
    }

    public Integer getGrey() {
        return grey;
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
}
