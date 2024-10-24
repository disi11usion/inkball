package inkball.entity.GameEntity;


import inkball.App;
import inkball.config.Score;
import inkball.entity.CollideLine.MouseLine;
import processing.core.PVector;

import java.util.List;
import java.util.Objects;

import static java.lang.Math.max;

public class Ball extends ImageEntity {
    private PVector velocity;
    private final double radious;
    private final PVector centerPosition;

    public boolean isGetInHoled() {
        return getInHoled;
    }

    private boolean getInHoled = false;
    private float scale = 1;

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    public PVector getPosition() {
        return this.position;
    }

    @Override
    public void draw() {
        position.add(velocity);
        centerPosition.add(velocity);
        app.image(ImageCache.ballsCache[color], position.x, position.y);
    }

    public Ball(int x, int y, Integer color) {
        super(x, y, color);
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

    public void draw(MouseLine mouseLine, List<Wall> walls, List<Hole> holes) {
        if (checkInHole(holes))
            return;
        ImageCache.ballsCache[color].resize((int) (24 * scale), (int) (24 * scale));
        app.image(ImageCache.ballsCache[color], position.x, position.y);
        if (scale < 1) {
            ImageCache.refreshBallCache(app);
        }
        this.checkMouseLine(mouseLine);
        this.checkWalls(walls);
        position.add(velocity);
        centerPosition.add(velocity);
    }

    private void checkWalls(List<Wall> walls) {
        for (Wall wall : walls) {
            List<PVector> points = wall.getPoints();
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

    private void checkMouseLine(MouseLine mouseLine) {
        for (int i = 0; i < mouseLine.mouseLines.size(); i++) {
            List<PVector> pVectors = mouseLine.mouseLines.get(i).getCurrentLine();
            for (int j = 0; j < pVectors.size() - 1; j++) {
                PVector p1 = pVectors.get(j);
                PVector p2 = pVectors.get(j + 1);
                if (checkCollide(p1, p2)) {
                    //System.out.println("MouseLine Collose");
                    getCollide(p1, p2);
                    mouseLine.mouseLines.remove(i);
                    return;
                }
            }
        }
    }

    private boolean checkCollide(PVector p1, PVector p2) {
        float dist1 = PVector.dist(p1, PVector.add(centerPosition, velocity));
        float dist2 = PVector.dist(p2, PVector.add(centerPosition, velocity));
        float dist3 = PVector.dist(p1, p2);
        return dist1 + dist2 < dist3 + radious + 0.1;
    }


    private void getCollide(PVector p1, PVector p2) {
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

//    private double cosAngleC(double a, double b, double c) {
//        double aSquare = a * a;
//        double bSquare = b * b;
//        double cSquare = c * c;
//        return (aSquare + bSquare - cSquare) / (2 * a * b);
//    }

    private boolean checkInHole(List<Hole> holes) {
        PVector scaledCenterPosition = new PVector(position.x + scale * 12,
                position.y + scale * 12);
        for (Hole hole : holes) {
            float dist = PVector.dist(scaledCenterPosition, hole.getCentralPoint());
            if (dist <= this.radious) {
                App.score += calculateScore(hole);
                getInHoled = true;
                return true;
            }
            if (dist <= hole.radius) {
                PVector t = PVector.sub(hole.getCentralPoint(), scaledCenterPosition);
                PVector force = PVector.div(t, 200);
                this.scale = (float) max(dist / hole.radius, 0.5);
                velocity.add(force);
            }
        }
        return false;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    private double calculateScore(Hole hole) {
        if (Objects.equals(this.color, hole.getColor())) {
            Score rightScore = App.gameConfig.getScoreRight();
            return rightScore.getScoreAfterMod().get(this.color);
        } else {
            Score wrongScore = App.gameConfig.getScoreWrong();
            if (this.color != 0 && hole.getColor() != 0)
                reBornBalls();
            return 0 - wrongScore.getScoreAfterMod().get(this.color);
        }
    }


    private void reBornBalls() {
        Spawner randomSpawner = App.activeLayout.getRandomSpawner();
        Ball rebornBall = new Ball(randomSpawner.getOrignalX(), randomSpawner.getOrignalY(), this.color
        );
        App.activeLayout.getBornBalls().add(rebornBall);
        Ball displayBall = new Ball(0, 0, this.color);
        List<Ball> allUnBornBalls = app.topbar.getAllUnBornBalls();
        allUnBornBalls.add(displayBall);
        displayBall.setVelocity(new PVector(0, 0));
        displayBall.setPosition(new PVector(allUnBornBalls.size() * CELLSIZE + 16-app.topbar.getOffset(),
                24));
    }
}
