package game;

public abstract class DamageEntity extends GameEntity {
    protected int health;

    public DamageEntity() {
        this(0, 0);
    }

    public DamageEntity(int x, int y) {
        super(x, y);
    }

    public void tick() {
        if (!isTicking)
            return;
        if (health <= 0)
            return;
        t++;
        update();
    }
    
    public void damage(int damage) {
        health -= damage;
    }
}
