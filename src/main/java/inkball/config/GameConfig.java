package inkball.config;

import inkball.utils.JsonConfigReader;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameConfig {
    private final Score scoreRight;
    private final Score scoreWrong;

    private final List<Level> levels = new ArrayList<>();
    private int currentLevelNumber = 0;

    public GameConfig(String fileName) {
        JSONObject configObject = JsonConfigReader.CreateJsonConfig(fileName);
        JSONArray jsonLevels = configObject.getJSONArray("levels");
        this.scoreRight = new Score(configObject.getJSONObject(
                "score_increase_from_hole_capture"));
        this.scoreWrong = new Score(
                configObject.getJSONObject("score_decrease_from_wrong_hole"));
        for (int i = 0; i < 3; i++) {
            levels.add(new Level(jsonLevels.getJSONObject(i)));
        }
    }

    public Score getScoreWrong() {
        return scoreWrong;
    }

    public Score getScoreRight() {
        return scoreRight;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setCurrentLevelNumber(int currentLevelNumber) {
        this.currentLevelNumber = currentLevelNumber;
        Level currentLevel = levels.get(this.currentLevelNumber);
        scoreRight.setModifier(currentLevel.getIncreaseModifier());
        scoreWrong.setModifier(currentLevel.getDecreaseModifier());
    }

    public Level getCurrentLevel() {
        return levels.get(currentLevelNumber);
    }
}
