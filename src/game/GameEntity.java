package game;

import java.awt.geom.Rectangle2D;

public abstract class GameEntity implements Drawable, Updatable {
    protected GameManager gameManager;
    protected int t, x, y, dx, dy;
    protected boolean isTicking;

    public GameEntity() {
        this(0, 0);
    }

    public GameEntity(int x, int y) {
        gameManager = GameManager.getGameManager();
        t = 0;
        isTicking = true;
        this.x = x;
        this.y = y; 
        dx = dy = 0;
        gameManager.addGameEntity(this);
    }

    public void tick() {
        if (!isTicking)
            return;
        update();
    }

    public void setTicking(boolean isTicking) {
        this.isTicking = isTicking;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract Rectangle2D[] getBounds();
}
