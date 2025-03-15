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
        t++;
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

    public void moveLeft() {
        dx = -1;
    }

    public void moveRight() {
        dx = 1;
    }

    public void moveUp() {
        dy = -1;
    }

    public void moveDown() {
        dy = 1;
    }

    public void move(int x, int y) {
        dx = Math.clamp(x, -1, 1);
        dy = Math.clamp(y, -1, 1);
    }

    public void stay() {
        dy = dx = 0;
    }
    public abstract Rectangle2D[] getBounds();
}
