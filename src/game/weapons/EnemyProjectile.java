package game.weapons;

import java.awt.geom.Rectangle2D;
import java.util.Collection;

import game.DamageEntity;
import game.Enemy;
import game.GameEntity;

public abstract class EnemyProjectile extends Projectile {
    public EnemyProjectile(GameEntity source) {
        super(source);
    }

    public void checkCollisions(Collection<DamageEntity> damageEntities) {
        if (!isActive)
            return;
        for (DamageEntity d : damageEntities) {
            if (d instanceof Enemy)
                continue;
            if (!d.equals(source)) {
                for (Rectangle2D r : d.getBounds()) {
                    for (Rectangle2D r2 : getBounds()) {
                        if (r.contains(r2))
                            d.damage(damage);
                    }
                }
            }
        }
    }
}
