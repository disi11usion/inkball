package inkball;

import inkball.config.GameConfig;
import inkball.config.Level;
import inkball.entity.GameEntity.Ball;
import inkball.entity.GameEntity.ImageCache;
import inkball.entity.GameEntity.ImageEntity;
import inkball.entity.CollideLine.Line;
import inkball.entity.CollideLine.MouseLine;
import inkball.layout.Layout;
import inkball.layout.Topbar;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.*;

public class App extends PApplet {

    public static final int CELLSIZE = 32; //8;
    public static final int CELLHEIGHT = 32;

    public static final int CELLAVG = 32;
    public static final int TOPBAR = 64;
    public static int WIDTH = 576; //CELLSIZE*BOARD_WIDTH;
    public static int HEIGHT = 640; //BOARD_HEIGHT*CELLSIZE+TOPBAR;
    public static final int BOARD_WIDTH = WIDTH / CELLSIZE;
    public static final int BOARD_HEIGHT = 20;

    public static final int INITIAL_PARACHUTES = 1;

    public static final int FPS = 30;

    public String configPath;
    public static boolean isPaused = false;
    public static Random random = new Random();
    public static GameConfig gameConfig;
    public static Layout activeLayout;
    public static MouseLine mouseLine = new MouseLine();
    public Line currentLine = new Line();
    // Feel free to add any additional methods or attributes you want. Please put classes in different files.
    public Topbar topbar;
    public static double score = 0;
    public static double totalScore = 0;
    public static double runningTime = 0;
    public static double levelTime = 0;
    public static int currentLevelNumber = 0;
    public Level currentLevel;
    public static boolean timeRunout = false;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        ImageEntity.setApp(this);
        Line.setApp(this);
        runningTime=0;
        currentLevelNumber = 0;
        totalScore = 0;
        gameConfig = new GameConfig(this.configPath);
        ImageCache.initImageCache(this);
        gameConfig.setCurrentLevelNumber(currentLevelNumber);

    }

    /**
     * Load all resources such as images. Initialise the elements such as the player and map elements.
     */
    @Override
    public void setup() {
        frameRate(FPS);
        gameConfig.setCurrentLevelNumber(currentLevelNumber);
        currentLevel = gameConfig.getCurrentLevel();
        activeLayout = new Layout(currentLevel);
        topbar = new Topbar(this);
        score = 0;
        levelTime = 0;
        mouseLine = new MouseLine();
        currentLine = new Line();
        timeRunout = false;
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == 32) {
            timePause(event);
        }
        if (event.getKey() == 'r') {
            if (currentLevelNumber==3){
                settings();
            }
            this.setup();
            loop();
        }
        if (event.getKey() == 'n') {
            totalScore += score;
            totalScore += (currentLevel.getTime() - levelTime) / 0.067;
            currentLevelNumber += 1;
            if (currentLevelNumber == 3) {
                timeRunout = true;
            } else
                setup();
        }
    }

    /**
     * Receive key released signal from the keyboard.
     */
    @Override
    public void keyReleased() {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // create a new player-drawn line object
        if (mouseButton == LEFT)
            this.currentLine = new Line();
        if (mouseButton == RIGHT) {
            PVector mousePos = new PVector(e.getX(), e.getY());
            for (Line mouseLine : mouseLine.mouseLines) {
                for (PVector pVector : mouseLine.getCurrentLine()) {
                    float dist = PVector.dist(mousePos, pVector);
                    if (dist < 10) {
                        App.mouseLine.mouseLines.remove(mouseLine);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // add line segments to player-drawn line object if left mouse button is held

        // remove player-drawn line object if right mouse button is held
        // and mouse position collides with the line
        if (mouseButton == LEFT) {
            currentLine.getCurrentLine().add(new PVector(e.getX(), e.getY()));

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseLine.mouseLines.add(currentLine);
        currentLine = new Line();
    }

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {

        //----------------------------------
        //display Board for current level:
        //----------------------------------
        topbar.draw(isPaused);
        //TODO
        if (!isPaused) {
            activeLayout.drawLayout();
            for (Ball ball : activeLayout.getBalls()) {
                ball.draw(mouseLine, activeLayout.getWalls(), activeLayout.getHoles());
                if (ball.isGetInHoled()) {
                    activeLayout.getBalls().remove(ball);
                    break;
                }
            }
            runningTime += 1 / (double) FPS;
            levelTime += 1 / (double) FPS;
        }
        //System.out.println(this.frameRate);
        //----------------------------------
        //----------------------------------
        //display game end message
        currentLine.drawLine();
        mouseLine.drawLines();
        //----------------------------------
        //display score
        //----------------------------------
        if (activeLayout.getBalls().isEmpty() && activeLayout.getBornBalls().isEmpty()) {
            totalScore += score;
            totalScore += (currentLevel.getTime() - levelTime) / 0.067;
            currentLevelNumber += 1;
            if (currentLevelNumber == 3) {
                timeRunout = true;
            } else
                setup();
        }
        if (levelTime > currentLevel.getTime()) {
            timeRunout = true;
        }
        // System.out.println(frameRate);
    }


    public static void main(String[] args) {
        PApplet.main("inkball.App");
    }

    public void timePause(KeyEvent event) {
        isPaused = !isPaused;
    }

}
