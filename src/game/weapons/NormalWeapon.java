package game.weapons;

import game.GameEntity;
import game.GameManager;

public class NormalWeapon extends Weapon {
    private final int AMMO_AMT = 50;
    private Projectile[] pool;
    private GameManager gameManager;
    private int current;

    public NormalWeapon(GameEntity owner) {
        super(owner);
        gameManager = GameManager.getGameManager();
        gameManager.addUpdatable(this);
        pool = new Projectile[AMMO_AMT];
        for (int i = 0; i < AMMO_AMT; i++) {
            pool[i] = new NormalProjectile(owner);
        }
        current = 0;
    }

    public void fire() {
        if (cooldown > 0)
            return;
        cooldown = 25;
        while (pool[current].isActive()) {
            current = (current + 1) % AMMO_AMT;
        }
        pool[current].fire(aimX, aimY);
    }

    public void update() {
        super.update();
    }
}
