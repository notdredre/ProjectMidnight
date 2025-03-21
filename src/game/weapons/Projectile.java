package game.weapons;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;

import game.CollisionChecker;
import game.DamageEntity;
import game.GameEntity;

public abstract class Projectile extends GameEntity implements CollisionChecker {
    protected int damage, range, offsetX, offsetY;
    protected GameEntity source;
    protected boolean isActive;
    protected ArrayList<DamageEntity> collided;

    public Projectile(GameEntity source, int offsetX, int offsetY) {
        super();
        this.source = source;
        isActive = false;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        collided = new ArrayList<>();
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
        x = source.getX() + offsetX;
        y = source.getY() + offsetY;
        isActive = true;
    }

    public abstract void reset();

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
                            if (!collided.contains(d)) {
                                d.damage(damage);
                                reset();
                            }            
                            collided.add(d);
                        }
                            
                    }
                }
            }
        }
    }

    
}
