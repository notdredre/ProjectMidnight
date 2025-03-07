package game.weapons;

import game.GameEntity;
import game.Updatable;

public abstract class Weapon implements Updatable {
    protected int cooldown, x, y;
    protected GameEntity owner;

    public Weapon(GameEntity owner) {
        this.owner = owner;
        x = owner.getX();
        y = owner.getY();
        cooldown = 0;
    }

    public void update() {
        x = owner.getX();
        y = owner.getY();
        cooldown--;
    }
    public abstract void fire();   
}