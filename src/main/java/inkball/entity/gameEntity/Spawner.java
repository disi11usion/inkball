package inkball.entity.gameEntity;

import inkball.entity.ImageCache;
import inkball.entity.ImageEntity;

public class Spawner extends ImageEntity {
    public Spawner(int x, int y, String name) {
        super(x, y, null, name);
    }

    @Override
    public void draw() {
        app.image(ImageCache.spawnCache,position.x, position.y);
    }
}

