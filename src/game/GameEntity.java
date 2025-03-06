package game;

public abstract class GameEntity implements Drawable, Updatable {
    private GameManager gameManager;
    protected int t, x, y;
    private boolean isTicking;

    public GameEntity() {
        gameManager = GameManager.getGameManager();
        t = 0;
        isTicking = false;
        gameManager.addGameEntity(this);
    }

    public void update() {
        if (!isTicking)
            return;

        t++;
    }

    public void setTicking(boolean isTicking) {
        this.isTicking = isTicking;
    }
}
