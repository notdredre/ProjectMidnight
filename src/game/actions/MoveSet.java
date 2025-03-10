package game.actions;

import game.GameEntity;

public abstract class MoveSet {
    protected GameEntity owner;
    protected int targetX, targetY;

    public MoveSet(GameEntity owner) {
        this.owner = owner;
    }

    public void setTarget(int x, int y) {
        targetX = x;
        targetY = y;
    }
    public abstract void act(int t);
}
