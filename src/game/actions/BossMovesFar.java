package game.actions;

import game.BossEnemy;
import game.GameEntity;
import game.GameManager;
import game.TargetPlayer;

public class BossMovesFar extends MoveSet implements TargetPlayer {
    private final int NUM_STEPS = 1000;
    private BossEnemy enemy;
    private int playerX, playerY;
    public BossMovesFar(GameEntity owner) {
        super(owner);
        enemy = (BossEnemy) owner;
        GameManager gameManager = GameManager.getGameManager();
        gameManager.addTargetPlayer(this);
    }

    public void updatePlayerLocation(int x, int y) {
        playerX = x;
        playerY = y;
    }

    @Override
    public void act(int t) {
        int step = t % NUM_STEPS;
        enemy.basicAttack();
        if (step == 600)
            enemy.stay();
        if (step > 800) {
            enemy.moveLeft();
        }
        if (step < 600) {
            enemy.move(0, playerY - enemy.getY());
        }
        if (step == 999) {
            enemy.stay();
        }
        if (enemy.getX() >= 400)
            enemy.moveLeft();
        if (enemy.getY() >= 400)
            enemy.moveUp();
        if (Math.abs(playerX - enemy.getX()) < 100 && Math.abs(playerY - enemy.getY()) < 100 || enemy.getX() <= 100 || enemy.getY() <= 100) {
            enemy.changeMoveSet(new BossMovesClose(owner));
        } 
    }
    
}
