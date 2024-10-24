package inkball.entity.GameEntity;

public class Spawner extends ImageEntity {
    private final int orignalX;
    private final int orignalY;

    public Spawner(int x, int y, String name) {
        super(x, y, null);
        orignalX = x;
        orignalY = y;
    }

    public int getOrignalX() {
        return orignalX;
    }

    public int getOrignalY() {
        return orignalY;
    }

    @Override
    public void draw() {
        app.image(ImageCache.spawnCache, position.x, position.y);
    }
}

