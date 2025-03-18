package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

import game.DamageEntity;
import game.GameEntity;
import game.sound.Sound;

public class Laser extends Projectile {
    private Sound fireSound;

    public Laser(GameEntity source, int offsetX, int offsetY) {
        super(source, offsetX, offsetY);
        damage = 15;
        range = 50;
        fireSound = new Sound("game/res/sfx/Laser.wav", 0.62f);
    }

    @Override
    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.PINK);
            g2.fillRect(x, y, 1000, 10);
        }
    }

    public void fire(int aimX, int aimY) {
        if (!fireSound.isPlaying()) {
            fireSound.play();
        }
            
        super.fire(aimX, aimY);
    }

    public void update() {
        if (!isActive)
            fireSound.stop();
        super.update();
    }

    public void reset() {
        isActive = false;
        t = 0;
        range = 50;
    }

    public void checkCollisions(Collection<DamageEntity> damageEntities) {
        if (!isActive)
            return;
        for (DamageEntity d : damageEntities) {
            if (!d.equals(source)) {
                for (Rectangle2D r : d.getBounds()) {
                    for (Rectangle2D r2 : getBounds()) {
                        if (r.intersects(r2)) {
                            if (t % 10 == 0)
                                d.damage(damage);
                        }
                            
                    }
                }
            }
        }
    }

    public Rectangle2D[] getBounds() {
        if (!isActive)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 1000, 10);
        return bounds;
    }
}
