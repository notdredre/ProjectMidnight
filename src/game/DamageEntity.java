package game;

import game.sound.Sound;

public abstract class DamageEntity extends GameEntity {
    protected int health;
    protected Sound explosion;

    public DamageEntity() {
        this(0, 0);
    }

    public DamageEntity(int x, int y) {
        super(x, y);
        explosion = new Sound("src/game/res/sfx/Explosion 1.wav", 0.7f);
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
        if (health <= 0)
            explosion.play();
    }
}
