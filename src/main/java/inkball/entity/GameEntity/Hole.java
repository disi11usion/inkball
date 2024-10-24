package inkball.entity.GameEntity;

import processing.core.PVector;

public class Hole extends ImageEntity {
    public PVector centralPoint;
    public final float radius = 32;

    public Hole(int x, int y, Integer color, String name) {

        super(x, y, color);
        this.centralPoint = PVector.add(this.position, new PVector(32, 32));
    }

    @Override
    public void draw() {
        app.image(ImageCache.holesCache[color], position.x, position.y);
    }

}
