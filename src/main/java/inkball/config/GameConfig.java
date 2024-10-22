package inkball.config;

import inkball.utils.JsonConfigReader;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameConfig {
    private JSONObject configObject;
    private JSONArray jsonLevels;
    private Score scoreRight;
    private Score scoreWrong;

    private List<Level> levels = new ArrayList<>();
    private int currentLevelNumber = 0;

    public List<Level> getLevelList() {
        return levels;
    }

    public GameConfig(String fileName) {
        this.configObject = JsonConfigReader.CreateJsonConfig(fileName);
        this.jsonLevels = this.configObject.getJSONArray("levels");
        this.scoreRight = new Score(this.configObject.getJSONObject(
                "score_increase_from_hole_capture"));
        this.scoreWrong = new Score(
                this.configObject.getJSONObject("score_decrease_from_wrong_hole"));
        for (int i = 0; i < 3; i++) {
            levels.add(new Level(this.jsonLevels.getJSONObject(i)));
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
