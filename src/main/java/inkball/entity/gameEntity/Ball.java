package inkball.entity.gameEntity;


import inkball.App;
import inkball.entity.ImageCache;
import inkball.entity.ImageEntity;
import inkball.entity.CollideLine.MouseLine;
import processing.core.PVector;

import java.util.List;

public class Ball extends ImageEntity {
    private PVector velocity;
    private final double radious;
    private final PVector centerPosition;

    @Override
    public void draw() {
        position.add(velocity);
        centerPosition.add(velocity);
        app.image(ImageCache.ballsCache[color], position.x, position.y);
    }

    public Ball(int x, int y, Integer color, String name) {
        super(x, y, color, name);
        int randomX = Math.random() > 0.5 ? 2 : -2;
        int randomY = Math.random() > 0.5 ? 2 : -2;
        if (App.FPS == 60) {
            randomX = randomX / 2;
            randomY = randomY / 2;
        }
        this.velocity = new PVector(randomX, randomY);
        this.radious = 12;
        this.centerPosition = new PVector((float) (position.x + radious),
                (float) (position.y + radious));
    }

    public void draw(MouseLine mouseLine, List<Wall> walls) {
        app.image(ImageCache.ballsCache[color], position.x, position.y);
        this.checkMouseLine(mouseLine);
        this.checkWalls(walls);
        position.add(velocity);
        centerPosition.add(velocity);
    }

    public void checkWalls(List<Wall> walls) {
        for (Wall wall : walls) {
            List<PVector> points = wall.points;
            for (int i = 0; i < points.size() - 1; i++) {
                PVector p1 = points.get(i);
                PVector p2 = points.get(i + 1);
                if (checkCollide(p1, p2)) {
                    //System.out.println("Wall Collose");
                    getCollide(p1, p2);
                    int wallColor = wall.getColor();
                    if (wallColor != 0) this.color = wallColor;
                    return;
                }
            }
            PVector p2 = points.get(0);
            PVector p1 = points.get(points.size() - 1);
            if (checkCollide(p1, p2)) {
                // System.out.println("Wall Collose");
                getCollide(p1, p2);
                int wallColor = wall.getColor();
                if (wallColor != 0) this.color = wallColor;
                return;
            }
        }
    }

    public void checkMouseLine(MouseLine mouseLine) {
        for (int i = 0; i < mouseLine.allMouseLines.size(); i++) {
            List<PVector> pVectors = mouseLine.allMouseLines.get(i).getCurrentLine();
            for (int j = 0; j < pVectors.size() - 1; j++) {
                PVector p1 = pVectors.get(j);
                PVector p2 = pVectors.get(j + 1);
                if (checkCollide(p1, p2)) {
                    //System.out.println("MouseLine Collose");
                    getCollide(p1, p2);
                    mouseLine.allMouseLines.remove(i);
                    return;
                }
            }
        }
    }

    public boolean checkCollide(PVector p1, PVector p2) {
        double d1 = PVector.dist(p1, PVector.add(centerPosition, velocity));
        double d2 = PVector.dist(p2, PVector.add(centerPosition, velocity));
        double d3 = PVector.dist(p1, p2) + radious;
        return d1 + d2 < d3;
    }


    public void getCollide(PVector p1, PVector p2) {
        PVector mid = PVector.add(p1, p2).div(2);
        PVector n1 = new PVector(p1.y - p2.y, p2.x - p1.x);
        PVector n2 = new PVector(p2.y - p1.y, p1.x - p2.x);
        double checkDistance1 = PVector.dist(centerPosition, PVector.add(mid, n1));
        double checkDistance2 = PVector.dist(centerPosition, PVector.add(mid, n2));
        PVector n;
        if (checkDistance1 > checkDistance2) {
            n = n1;
        } else {
            n = n2;
        }
        n.normalize();
        float v1 = 2 * PVector.dot(velocity, n);
        PVector v2 = PVector.mult(n, v1);
        velocity = PVector.sub(velocity, v2);
    }
}
