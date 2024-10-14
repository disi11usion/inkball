package inkball.entity.gameEntity;

import inkball.entity.ImageCache;
import inkball.entity.ImageEntity;

public class Tile  extends ImageEntity {
    @Override
    public void draw() {
        app.image(ImageCache.tileCache,position.x,position.y);
    }

    public Tile(int x, int y) {
        super(x, y,null,"tile");
    }
}
