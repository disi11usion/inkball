package inkball.entity;

import inkball.App;
import processing.core.PImage;

public class ImageCache {
    public static PImage[] wallsCache = new PImage[5];
    public static PImage[] ballsCache = new PImage[5];
    public static PImage[] holesCache = new PImage[5];
    public static PImage spawnCache;
    public static PImage tileCache;

    public static void InitImageCache(App app) {

        //sapwner
        spawnCache = app.loadImage("inkball/entrypoint.png");

        //tile
        tileCache = app.loadImage("inkball/tile.png");
        if (tileCache == null) {
            System.err.println("Error: Tile.png could not be loaded.");
        } else {
            System.err.println("Tile.png loaded successfully.");
        }

        //actual wall
        for (int i = 0; i < wallsCache.length; i++) {
            wallsCache[i] = app.loadImage("inkball/wall" + i + ".png");
            if (wallsCache[i] == null) {
                System.err.println("Error: wall" + i + ".png could not be loaded.");
            } else {
                System.err.println("wall" + i + ".png loaded successfully.");
            }
        }

        //hole
        for (int i = 0; i < holesCache.length; i++) {
            holesCache[i] = app.loadImage("inkball/hole" + i + ".png");
            if (holesCache[i] == null) {
                System.err.println("Error: hole" + i + ".png could not be loaded.");
            } else {
                System.err.println("hole" + i + ".png loaded successfully.");
            }
        }
        for (int i = 0; i < 5; i++) {
            ballsCache[i] = app.loadImage("inkball/ball" + i + ".png");
        }
    }
}
