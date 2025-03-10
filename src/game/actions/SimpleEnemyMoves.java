package game.actions;

import game.GameEntity;

public class SimpleEnemyMoves extends MoveSet {
    private final int NUM_STEPS = 1000;
    public SimpleEnemyMoves(GameEntity owner) {
        super(owner);
    }

    public void act(int t) {
        int step = t % NUM_STEPS;
        if (step < 300)
            owner.moveLeft();
        else if (step > 300 && step < 500)
            owner.moveDown();
        else
            owner.stay();
    }
}
