package game.weapons;

import game.GameEntity;

public class NormalWeapon extends Weapon {

    public NormalWeapon(GameEntity owner) {
        super(owner, 50);
        for (int i = 0; i < AMMO_AMT; i++) {
            pool[i] = new NormalProjectile(owner, 20, 10);
        }
        cooldown = 25;
    }
}
