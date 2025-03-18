package game;

import game.graphics.Animation;
import game.sound.Sound;

public abstract class DamageEntity extends GameEntity {
    protected int health;
    protected Sound explosion;
    protected Animation explosionAnim;

    public DamageEntity() {
        this(0, 0);
    }

    public DamageEntity(int x, int y) {
        super(x, y);
        explosion = new Sound("src/game/res/sfx/Explosion 1.wav", 0.7f);
        explosionAnim = new Animation(this, "src/game/res/sprites/Explosion1.gif", 1, 10, 33);
        explosionAnim.rowAnim("BOOM", 0);
        explosionAnim.setState("BOOM");
    }

    public int getHealth() {
        return health;
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
