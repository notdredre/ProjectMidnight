package game.weapons;

import game.GameEntity;

public class SpecialWeapon extends Weapon {
    public SpecialWeapon(GameEntity owner) {
        super(owner, 1);
        pool[0] = new Laser(owner, 15, 10);
    }

    public void fire() {
        pool[0].fire(0, 0);
    }
}
