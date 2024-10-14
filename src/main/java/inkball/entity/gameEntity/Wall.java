package inkball.entity.gameEntity;

import inkball.entity.ImageCache;
import inkball.entity.ImageEntity;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Wall extends ImageEntity {

    public List<PVector> points = new ArrayList<>();

    @Override
    public void draw() {
        app.image(ImageCache.wallsCache[color], position.x, position.y);
    }

    public Wall(int x, int y) {
        super(x, y, 0, "wall");
        points.add(this.position);
        points.add(new PVector(this.position.x+32, this.position.y));
        points.add(new PVector(this.position.x+32, this.position.y+32));
        points.add(new PVector(this.position.x, this.position.y + 32));

    }

    public Wall(int x, int y, int color, String name) {
        super(x, y, color, name);
        points.add(this.position);
        points.add(new PVector(this.position.x+32, this.position.y));
        points.add(new PVector(this.position.x+32, this.position.y+32));
        points.add(new PVector(this.position.x, this.position.y + 32));
    }

    public int getColor() {
        return this.color;
    }
}
