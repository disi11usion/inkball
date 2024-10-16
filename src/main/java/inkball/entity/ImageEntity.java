package inkball.entity;

import inkball.App;
import processing.core.PVector;


abstract public class ImageEntity {
    protected PVector position;
    protected Integer color;
    protected String path;
    protected static final int CELLSIZE = 32; //8;
    protected static final int CELLHEIGHT = 32;
    protected static final int TOPBAR = 64;
    static protected App app;

    public ImageEntity(int x, int y, Integer color, String name) {
        position = new PVector(x * CELLSIZE, y * CELLSIZE + TOPBAR);
        this.color = color;
    }


    public static void setApp(App app) {
        ImageEntity.app = app;
    }

    abstract public void draw();

}
