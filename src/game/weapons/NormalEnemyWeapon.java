package game.weapons;

import game.GameEntity;
public class NormalEnemyWeapon extends Weapon {

    public NormalEnemyWeapon(GameEntity owner) {
        super(owner, 50);
        for (int i = 0; i < AMMO_AMT; i++) {
            pool[i] = new NormalEnemyProjectile(owner, 5, 10);
        }
        cooldown = 100;
    }
}
