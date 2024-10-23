package inkball.entity.gameEntity;

import inkball.entity.ImageCache;
import inkball.entity.ImageEntity;

public class Spawner extends ImageEntity {
    public int orignalX;
    public int orignalY;

    public Spawner(int x, int y, String name) {
        super(x, y, null, name);
        orignalX = x;
        orignalY = y;
    }

    @Override
    public void draw() {
        app.image(ImageCache.spawnCache, position.x, position.y);
    }
}

