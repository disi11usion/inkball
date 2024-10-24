package inkball.config;

import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.Arrays;
import java.util.List;

public class Level {
    private final String layoutPath;
    private final Integer time;
    private final Integer spawnInterval;
    private final Double increaseModifier;
    private final Double decreaseModifier;
    private final List<String> unBornBalls;

    public String getLayoutPath() {
        return layoutPath;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getSpawnInterval() {
        return spawnInterval;
    }

    public Double getIncreaseModifier() {
        return increaseModifier;
    }

    public Double getDecreaseModifier() {
        return decreaseModifier;
    }

    public List<String> getBalls() {
        return unBornBalls;
    }

    public Level(JSONObject jsonObject) {
        layoutPath = jsonObject.getString("layout");
        time = jsonObject.getInt("time");
        spawnInterval = jsonObject.getInt("spawn_interval");
        increaseModifier = jsonObject.getDouble("score_increase_from_hole_capture_modifier");
        decreaseModifier = jsonObject.getDouble("score_decrease_from_wrong_hole_modifier");
        JSONArray jsonBalls = jsonObject.getJSONArray("balls");
        unBornBalls = Arrays.asList(jsonBalls.getStringArray());
    }
}
