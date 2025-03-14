package game.actions;

import game.BossEnemy;
import game.GameEntity;

public class BossMovesClose extends MoveSet {
    private BossEnemy enemy;
    public BossMovesClose(GameEntity owner) {
        super(owner);
        enemy = (BossEnemy) owner;
    }

    @Override
    public void act(int t) {
        
    }
    
}
