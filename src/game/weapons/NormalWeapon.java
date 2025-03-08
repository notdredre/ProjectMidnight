package game.weapons;

import game.GameEntity;
import game.GameManager;

public class NormalWeapon extends Weapon {
    private Projectile projectile;
    private GameManager gameManager;

    public NormalWeapon(GameEntity owner) {
        super(owner);
        gameManager = GameManager.getGameManager();
        gameManager.addUpdatable(this);
    }

    public void fire() {
        if (cooldown > 0)
            return;

        cooldown = 25;
        projectile = new Projectile(x, y, aimX, aimY, owner);
    }

    public void update() {
        super.update();
    }
}
