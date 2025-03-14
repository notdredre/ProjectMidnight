package game.actions;

import game.BossEnemy;
import game.GameEntity;

public class BossMovesFar extends MoveSet {
    private final int NUM_STEPS = 1000;
    private BossEnemy enemy;
    public BossMovesFar(GameEntity owner) {
        super(owner);
        enemy = (BossEnemy) owner;
    }
    @Override
    public void act(int t) {
        int step = t % NUM_STEPS;
        enemy.basicAttack();
        if (step == 999 || step == 600)
            enemy.stay();
        if (step > 800) {
            enemy.moveLeft();
        }
        if (step < 600) {
            enemy.move(0, enemy.getPlayerY() - enemy.getY());
        }
        if (step == 999) 
            enemy.changeMoveSet(new BossMovesClose(owner));
    }
    
}
