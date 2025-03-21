package game;

import game.weapons.Weapon;

public abstract class Enemy extends DamageEntity implements TargetPlayer {
    protected Weapon[] weapons;
    protected int playerX, playerY;
    protected int value;

    public Enemy() {
        this(0, 0);
    }

    public void updateScore() {
        gameManager.updateScore(value);
    }
    
    public Enemy(int x, int y) {
        super(x, y);
        gameManager.addTargetPlayer(this);
        isTicking = false;
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

    public void stay() {
        dy = dx = 0;
    }
    
    public void updatePlayerLocation(int x, int y) {
        playerX = x;
        playerY = y;
    }
}
