package inkball.entity.gameEntity;

import inkball.entity.ImageCache;
import inkball.entity.ImageEntity;

public class Hole extends ImageEntity {
    public Hole(int x, int y, Integer color, String name) {
        super(x, y, color, name);
    }

    @Override
    public void draw() {
        app.image(ImageCache.holesCache[color],position.x, position.y);
    }
}
