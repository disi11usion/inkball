package inkball.layout;

import inkball.App;

public class Topbar {
    private App app;
    private static int WIDTH = 576;
    public static final int TOPBAR = 64;
    private static int CELLSIZE = 32;

    public Topbar(App app) {
        this.app = app;
    }

    public void draw() {
        app.fill(200);
        app.noStroke();
        app.rect(0, 0, WIDTH, Topbar.TOPBAR);
        app.fill(0);
        app.textSize(20);
        app.text("Score: " + App.score, WIDTH - 5 * CELLSIZE, CELLSIZE);
        app.text("Time: " + app.getGameSecond(), WIDTH - 5 * CELLSIZE, CELLSIZE + 24);

    }
}
