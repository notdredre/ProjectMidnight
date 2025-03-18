package game.weapons;

import game.GameEntity;

public class EnemyGrenadeWeapon extends Weapon {

    public EnemyGrenadeWeapon(GameEntity owner) {
        super(owner, 5);
        for (int i = 0; i < AMMO_AMT; i++) {
            pool[i] = new SplittingProjectile(owner);
        }
        cooldown = 150;
    }
}
