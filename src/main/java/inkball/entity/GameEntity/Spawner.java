package inkball.entity.GameEntity;

public class Spawner extends ImageEntity {
    public int orignalX;
    public int orignalY;

    public Spawner(int x, int y, String name) {
        super(x, y, null);
        orignalX = x;
        orignalY = y;
    }

    @Override
    public void draw() {
        app.image(ImageCache.spawnCache, position.x, position.y);
    }
}

