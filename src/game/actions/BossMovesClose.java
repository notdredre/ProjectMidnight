package game.actions;

import game.BossEnemy;
import game.GameEntity;

public class BossMovesClose extends MoveSet {
    private BossEnemy enemy;
    private int t0;
    public BossMovesClose(GameEntity owner) {
        super(owner);
        enemy = (BossEnemy) owner;
        t0 = -1;
    }

    @Override
    public void act(int t) {
        if (t0 == -1) {
            if (enemy.getX() != 100 || enemy.getY() != 100) {
                t0 = -1;
                enemy.move(100 - enemy.getX(), 100 - enemy.getY());
            } else {
                t0 = t;
                enemy.stay();
            }
        }
        if (t0 != -1) {
            enemy.moveRight();
            enemy.moveDown();
            enemy.specialAttack();
        }
        if (enemy.getX() >= 400 || enemy.getY() >= 400)
            enemy.changeMoveSet(new BossMovesFar(owner));  
    }
    
}
