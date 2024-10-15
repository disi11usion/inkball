package inkball;

import inkball.config.GameConfig;
import inkball.entity.gameEntity.Ball;
import inkball.entity.ImageCache;
import inkball.entity.ImageEntity;
import inkball.entity.CollideLine.CurrentLine;
import inkball.entity.CollideLine.MouseLine;
import inkball.layout.Layout;
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

    public static Random random = new Random();
    public static GameConfig gameConfig;
    public static Layout activeLayout;
    public static MouseLine mouseLine = new MouseLine();
    public CurrentLine currentLine = new CurrentLine();
    // Feel free to add any additional methods or attributes you want. Please put classes in different files.

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player and map elements.
     */
    @Override
    public void setup() {
        ImageEntity.setApp(this);
        CurrentLine.setApp(this);
        ImageCache.InitImageCache(this);
        frameRate(FPS);
        gameConfig = new GameConfig(this.configPath);
        activeLayout = new Layout(gameConfig.getLevels().get(1));
        //See PApplet javadoc:
        //loadJSONObject(configPath)
        // the image is loaded from relative path: "src/main/resources/inkball/..."
		/*try {
            result = loadImage(URLDecoder.decode(this.getClass().getResource(filename+".png").getPath(), StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }*/
        activeLayout.drawLayout();
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(KeyEvent event) {

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
        this.currentLine = new CurrentLine();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // add line segments to player-drawn line object if left mouse button is held

        // remove player-drawn line object if right mouse button is held
        // and mouse position collides with the line

        currentLine.getCurrentLine().add(new PVector(e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseLine.allMouseLines.add(currentLine);
        currentLine = new CurrentLine();
    }

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {

        //----------------------------------
        //display Board for current level:
        //----------------------------------
        //TODO

        activeLayout.drawLayout();
        for (Ball ball : activeLayout.getBalls()) {
            ball.draw(mouseLine, activeLayout.getWalls());
        }
        currentLine.drawCurrentLine();
        mouseLine.drawLines();
        //----------------------------------
        //display score
        //----------------------------------
        //TODO

        //----------------------------------
        //----------------------------------
        //display game end message


       // System.out.println(frameRate);
    }


    public static void main(String[] args) {
        PApplet.main("inkball.App");
    }

}
