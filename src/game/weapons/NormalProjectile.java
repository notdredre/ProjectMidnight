package game.weapons;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import game.GameEntity;
import game.graphics.Sprite;
import game.sound.Sound;

public class NormalProjectile extends Projectile {
    private Sound fireSound;
    private Sprite projectileSprite;

    public NormalProjectile(GameEntity source, int offsetX, int offsetY) {
        super(source, offsetX, offsetY);
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
        isActive = false;
        t = 0;
        range = 500;
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            projectileSprite.draw(g2);
        }
    }

    public Rectangle2D[] getBounds() {
        if (!isActive)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x - 2, y - 2, 12, 12);
        return bounds;
    }
}
