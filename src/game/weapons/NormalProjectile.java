package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GameEntity;
import game.graphics.Sprite;
import game.sound.Sound;

public class NormalProjectile extends Projectile {
    private Sound fireSound;
    private Sprite projectileSprite;

    public NormalProjectile(GameEntity source) {
        super(source);
        damage = 5;
        range = 500;
        fireSound = new Sound("src/game/res/sfx/Normal Weapon.wav", 0.7f);
        projectileSprite = new Sprite(this, "src/game/res/sprites/Projectile.gif");
    }

    public void fire(int aimX, int aimY) {
        fireSound.play();
        super.fire(aimX, aimY);
    }

    public void reset() {
        super.reset();
        range = 500;
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            projectileSprite.draw(g2);
        }
    }
}
