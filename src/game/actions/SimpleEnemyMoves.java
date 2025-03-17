package game.actions;

import game.GameEntity;
import game.SimpleEnemy;

public class SimpleEnemyMoves extends MoveSet {
    private SimpleEnemy enemy;
    public SimpleEnemyMoves(GameEntity owner) {
        super(owner);
        this.enemy = (SimpleEnemy) owner;
    }

    public void act(int t) {
        if (enemy.getX() < 350)
            enemy.moveRight();
        else {
            if (t < 1000) {
                enemy.stay();
                enemy.attack();
            }
            else
                enemy.moveUp();
        } 
    }
}
