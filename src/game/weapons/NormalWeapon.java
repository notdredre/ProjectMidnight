package game.weapons;

import game.GameEntity;
import game.GameManager;
import game.Player;

public class NormalWeapon extends Weapon {
    private Projectile[] pool;
    private GameManager gameManager;
    private int current;

    public NormalWeapon(GameEntity owner) {
        super(owner);
        gameManager = GameManager.getGameManager();
        gameManager.addUpdatable(this);
        pool = new Projectile[30];
        for (int i = 0; i < 30; i++) {
            pool[i] = new Projectile(owner);
        }
        current = 0;
    }

    public void fire() {
        if (cooldown > 0)
            return;
        cooldown = 25;
        while (pool[current].isActive) {
            current = (current + 1) % 30;
        }
        pool[current].fire(aimX, aimY);
    }

    public void update() {
        super.update();
    }
}
