package game;

public abstract class GameEntity implements Drawable, Updatable {
    private GameManager gameManager;
    protected int t, x, y, dx, dy;
    protected boolean isTicking;

    public GameEntity() {
        gameManager = GameManager.getGameManager();
        t = 0;
        isTicking = false;
        x = y = dx = dy = 0;
        gameManager.addGameEntity(this);
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
}
