package game.weapons;

import game.GameEntity;
import game.GameManager;
import game.Updatable;

public abstract class Weapon implements Updatable {
    protected int cooldown, currentCooldown, x, y, aimX, aimY;
    protected GameEntity owner;
    protected Projectile[] pool;
    protected GameManager gameManager;
    protected int current;
    protected int AMMO_AMT;

    public Weapon(GameEntity owner, int AMMO_AMT) {
        this.owner = owner;
        x = owner.getX();
        y = owner.getY();
        cooldown = 0;
        gameManager = GameManager.getGameManager();
        gameManager.addUpdatable(this);
        this.AMMO_AMT = AMMO_AMT;
        pool = new Projectile[AMMO_AMT];
    }

    public void update() {
        x = owner.getX();
        y = owner.getY();
        currentCooldown--;
    }

    public void fire() {
        if (currentCooldown > 0)
            return;
        currentCooldown = cooldown;
        while (pool[current].isActive()) {
            current = (current + 1) % AMMO_AMT;
        }
        pool[current].fire(aimX, aimY);
    }

    public void setAim(int aimX, int aimY) {
        this.aimX = aimX;
        this.aimY = aimY;
    }   
}