package game;

import game.weapons.Weapon;

public abstract class Enemy extends DamageEntity implements TargetPlayer {
    protected Weapon weapon;
    protected int playerX, playerY;

    public Enemy() {
        this(0, 0);
    }

    public Enemy(int x, int y) {
        super(x, y);
        gameManager.addTargetPlayer(this);
    }

    public void updatePlayerLocation(int x, int y) {
        playerX = x;
        playerY = y;
    }
}
