package game;

import java.util.Collection;

public interface CollisionChecker {
    public abstract void checkCollisions(Collection<DamageEntity> d);
}
