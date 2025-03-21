package game;

import game.graphics.Animation;
import game.graphics.PostFX;
import game.graphics.imagefx.FlashFX;
import game.sound.Sound;

public abstract class DamageEntity extends GameEntity {
    protected int health;
    protected Sound explosion;
    protected Animation explosionAnim;
    protected PostFX processor;

    public DamageEntity() {
        this(0, 0);
    }

    public DamageEntity(int x, int y) {
        super(x, y);
        explosion = new Sound("game/res/sfx/Explosion1.wav", 0.7f);
        explosionAnim = new Animation(this, "game/res/sprites/Explosion1.gif", 1, 10, 33);
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
        processor.setPostFX(new FlashFX());
        if (health <= 0)
            explosion.play();
    }
}
