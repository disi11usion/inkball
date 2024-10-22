package inkball.config;

import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.Arrays;
import java.util.List;

public class Level {
    private String layoutPath;
    private Integer time;
    private Integer spawnInterval;
    private Double increaseModifier;
    private Double decreaseModifier;
    private List<String> balls;
    public String getLayoutPath() {
        return layoutPath;
    }

    public void setLayoutPath(String layoutPath) {
        this.layoutPath = layoutPath;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getSpawnInterval() {
        return spawnInterval;
    }

    public void setSpawnInterval(Integer spawnInterval) {
        this.spawnInterval = spawnInterval;
    }

    public Double getIncreaseModifier() {
        return increaseModifier;
    }

    public void setIncreaseModifier(Double increaseModifier) {
        this.increaseModifier = increaseModifier;
    }

    public Double getDecreaseModifier() {
        return decreaseModifier;
    }

    public void setDecreaseModifier(Double decreaseModifier) {
        this.decreaseModifier = decreaseModifier;
    }

    public List<String> getBalls() {
        return balls;
    }

    public void setBalls(List<String> balls) {
        this.balls = balls;
    }

    public Level(JSONObject jsonObject) {
        layoutPath = jsonObject.getString("layout");
        time = jsonObject.getInt("time");
        spawnInterval = jsonObject.getInt("spawn_interval");
        increaseModifier = jsonObject.getDouble("score_increase_from_hole_capture_modifier");
        decreaseModifier = jsonObject.getDouble("score_decrease_from_wrong_hole_modifier");
        JSONArray jsonBalls = jsonObject.getJSONArray("balls");
        balls = Arrays.asList(jsonBalls.getStringArray());
    }
}
