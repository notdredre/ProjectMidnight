package game.weapons;

import java.awt.geom.Rectangle2D;
import java.util.Collection;

import game.CollisionChecker;
import game.DamageEntity;
import game.GameEntity;

public abstract class Projectile extends GameEntity implements CollisionChecker {
    protected int damage, range;
    protected GameEntity source;
    protected boolean isActive;

    public Projectile(GameEntity source) {
        super();
        this.source = source;
        isActive = false;
    }


    public void update() {
        if (range <= 0)
            reset();
        if (isActive) {
            x += dx;
            y += dy;
            range--;    
        }
    }

    public void fire(int aimX, int aimY) {
        dx = aimX;
        dy = aimY;
        x = source.getX();
        y = source.getY();
        isActive = true;
    }

    public void reset() {
        isActive = false;
        t = 0;
    }

    public boolean isActive() {
        return isActive;
    }

    public void checkCollisions(Collection<DamageEntity> damageEntities) {
        if (!isActive)
            return;
        for (DamageEntity d : damageEntities) {
            if (!d.equals(source)) {
                for (Rectangle2D r : d.getBounds()) {
                    for (Rectangle2D r2 : getBounds()) {
                        if (r.intersects(r2)) {
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
        bounds[0] = new Rectangle2D.Double(x, y, 5, 5);
        return bounds;
    }
}
