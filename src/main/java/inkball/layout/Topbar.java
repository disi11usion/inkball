package inkball.layout;

import inkball.App;
import inkball.config.Level;
import inkball.entity.gameEntity.Ball;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Topbar {
    private final App app;
    private static final int WIDTH = 576;
    public static final int TOPBAR = 64;
    private static final int CELLSIZE = 32;
    private final Layout layout;
    private final Level level;
    private final List<Ball> allUnBornBalls;
    private double intervalCounter;
    private boolean beginMove = false;
    public int offset = 0;

    public Topbar(App app) {
        this.app = app;
        this.layout = App.activeLayout;
        this.level = app.currentLevel;
        allUnBornBalls = new ArrayList<>();
        List<Ball> bornBalls = layout.getBornBalls();
        for (int i = 0; i < bornBalls.size(); i++) {
            PVector ballPosition = new PVector(
                    (16 + (i) * CELLSIZE),
                    24);
            Ball displayBall = new Ball(0, 0, bornBalls.get(i).getColor(), "displayBall");
            displayBall.setPosition(ballPosition);
            displayBall.setVelocity(new PVector(0, 0));
            allUnBornBalls.add(displayBall);
        }
        intervalCounter =
                level.getSpawnInterval() - (App.levelTime - layout.getSpawnIntervalTime());
    }

    public List<Ball> getAllUnBornBalls() {
        return allUnBornBalls;
    }

    public void draw(boolean isPaused) {
        app.fill(200);
        app.noStroke();
        app.rect(0, 0, WIDTH, Topbar.TOPBAR);
        app.fill(0);
        app.textSize(20);
        drawUnbornBalls();
        app.fill(200);
        app.rect(12 + 5 * CELLSIZE, 0, WIDTH, Topbar.TOPBAR);
        app.fill(0);
        if (isPaused) {
            app.text("*** PAUSED ***", (float) ((float) WIDTH / 2 - (2.5 * CELLSIZE)),
                    CELLSIZE + 12);
        }
        if (App.timeRunout) {
            if (App.currentLevelNumber == 3)
                app.text("=== ENDED === ", (float) ((float) WIDTH / 2 - (2.5 * CELLSIZE)),
                        CELLSIZE + 12);
            else
                app.text("=== TIME’S UP === ", (float) ((float) WIDTH / 2 - (2.5 * CELLSIZE)),
                        CELLSIZE + 12);
            app.noLoop();
        }
        if (allUnBornBalls.isEmpty()) {
            intervalCounter = 0;
        } else {
            intervalCounter =
                    level.getSpawnInterval() - (App.levelTime - layout.getSpawnIntervalTime()) +
                    0.5;
        }
        app.text(String.valueOf(
                        (int) intervalCounter),
                6 * CELLSIZE, 12 + CELLSIZE);
        int clock = (int) (app.currentLevel.getTime() - App.levelTime + 0.5);
        app.text("Score: " + (App.totalScore + App.score), WIDTH - 5 * CELLSIZE, CELLSIZE);
        app.text("Time: " + clock, WIDTH - 5 * CELLSIZE, CELLSIZE + 24);

    }

    public void drawUnbornBalls() {
        app.rect(12, 12, 12 + 5 * CELLSIZE, 12 + CELLSIZE);

        if (allUnBornBalls.size() > layout.getBornBalls().size()) {
            allUnBornBalls.remove(0);
            beginMove = true;
            if (allUnBornBalls.isEmpty()) {
                beginMove = false;
                return;
            }
        }
        if (beginMove) {
            offset += 1;
            for (Ball allUnBornBall : allUnBornBalls) {
                allUnBornBall.setVelocity(new PVector(-1, 0));
            }
            if (allUnBornBalls.get(0).getPosition().x <= 12 + 2) {
                beginMove = false;
            }
        }
        if (!beginMove) {
            for (Ball allUnBornBall : allUnBornBalls) {
                allUnBornBall.setVelocity(new PVector(0, 0));
            }
            offset = 0;
        }
        allUnBornBalls.forEach(Ball::draw);
    }

}
