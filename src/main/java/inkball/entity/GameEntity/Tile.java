package inkball.entity.GameEntity;

public class Tile  extends ImageEntity {
    @Override
    public void draw() {
        app.image(ImageCache.tileCache,position.x,position.y);
    }

    public Tile(int x, int y) {
        super(x, y,null);
    }
}
