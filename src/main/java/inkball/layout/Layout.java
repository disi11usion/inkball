package inkball.layout;

import inkball.App;
import inkball.config.Level;
import inkball.entity.GameEntity.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Layout {
    private final Level level;
    private final List<Wall> walls = new ArrayList<>();
    private final List<Tile> tiles = new ArrayList<>();
    private final List<Hole> holes = new ArrayList<>();
    private final List<Spawner> spawners = new ArrayList<>();
    private final List<Ball> balls = new ArrayList<>();
    private final List<Ball> bornBalls = new ArrayList<>();
    private double spawnIntervalTime = 0;

    public List<Wall> getWalls() {
        return walls;
    }


    public double getSpawnIntervalTime() {
        return spawnIntervalTime;
    }

    public List<Hole> getHoles() {

        return holes;
    }

    public Layout(Level level) {
        this.level = level;
        File layoutFile = new File(level.getLayoutPath());
        Scanner scanner;
        try {
            scanner = new Scanner(layoutFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c == ' ') {
                    Tile tile = new Tile(j, i);
                    tiles.add(tile);
                }
                if (c == 'X') {
                    Wall wall = new Wall(j, i);
                    walls.add(wall);
                }
                if (c == '1' || c == '2' || c == '3' || c == '4') {
                    if (j == 0 || (line.charAt(j - 1) != 'H') && (line.charAt(j - 1) != 'B')) {
                        Wall coloredWall = new Wall(j, i, c - 48, "wall");
                        walls.add(coloredWall);
                    }
                }
                if (c == 'H') {
                    int color = line.charAt(j + 1) - 48;
                    Hole hole = new Hole(j, i, color, "hole");
                    holes.add(hole);
                }
                if (c == 'S') {
                    Spawner spawner = new Spawner(j, i, "entrypoint");
                    spawners.add(spawner);
                }
                if (c == 'B') {
                    int color = line.charAt(j + 1) - 48;
                    Ball ball = new Ball(j, i, color, "ball");
                    balls.add(ball);
                    Tile tile1 = new Tile(j, i);
                    Tile tile2 = new Tile(j + 1, i);
                    tiles.add(tile1);
                    tiles.add(tile2);
                }
            }
        }
        setupUnBornBalls();
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void drawLayout() {
        tiles.forEach(ImageEntity::draw);
        walls.forEach(ImageEntity::draw);
        holes.forEach(ImageEntity::draw);
        spawners.forEach(ImageEntity::draw);
        if (App.levelTime - spawnIntervalTime >= level.getSpawnInterval()) {
            spawnIntervalTime = App.levelTime;
            if (!bornBalls.isEmpty()) {
                Ball newBall = bornBalls.get(0);
                balls.add(newBall);
                bornBalls.remove(0);
            }

        }
    }

    public List<Ball> getBornBalls() {
        return bornBalls;
    }

    private void setupUnBornBalls() {
        List<String> strBalls = level.getBalls();
        for (String strBall : strBalls) {
            if (strBall.equals("grey")) {
                Spawner randomSpawner = getRandomSpawner();
                bornBalls.add(new Ball(randomSpawner.orignalX, randomSpawner.orignalY, 0, "ball"));
            }
            if (strBall.equals("orange")) {
                Spawner randomSpawner = getRandomSpawner();
                bornBalls.add(new Ball(randomSpawner.orignalX, randomSpawner.orignalY, 1, "ball"));
            }
            if (strBall.equals("blue")) {
                Spawner randomSpawner = getRandomSpawner();
                bornBalls.add(new Ball(randomSpawner.orignalX, randomSpawner.orignalY, 2, "ball"));
            }
            if (strBall.equals("green")) {
                Spawner randomSpawner = getRandomSpawner();
                bornBalls.add(new Ball(randomSpawner.orignalX, randomSpawner.orignalY, 3, "ball"));
            }
            if (strBall.equals("yellow")) {
                Spawner randomSpawner = getRandomSpawner();
                bornBalls.add(new Ball(randomSpawner.orignalX, randomSpawner.orignalY, 4, "ball"));
            }
        }
    }

    public Spawner getRandomSpawner() {
        int len = spawners.size();
        return spawners.get((int) (Math.random() * len));
    }
}
