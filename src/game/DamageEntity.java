package game;

public abstract class DamageEntity extends GameEntity {
    protected int health;

    public DamageEntity() {
        this(0, 0);
    }

    public DamageEntity(int x, int y) {
        super(x, y);
    }
    public void damage(int damage) {
        health -= damage;
    }
}
