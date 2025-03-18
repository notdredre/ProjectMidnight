package game.weapons;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import game.GameEntity;
import game.graphics.Animation;
import game.graphics.Sprite;
import game.graphics.imagefx.FlashFX;
import game.sound.Sound;

public class SplittingProjectile extends EnemyProjectile {
    private Projectile[] fragments;
    private final int NUM_FRAGS = 3;
    private Sprite grenadeSprite;
    private Animation explosionAnim;
    private FlashFX flash;
    private Sound grenadeSound, explosionSound;

    public SplittingProjectile(GameEntity source, int offsetX, int offsetY) {
        super(source, offsetX, offsetY);
        damage = 5;
        range = 110;
        fragments = new NormalEnemyProjectile[NUM_FRAGS];
        for (int i = 0; i < NUM_FRAGS; i++)
            fragments[i] = new NormalEnemyProjectile(this, 0, 0);
        grenadeSprite = new Sprite(this, "game/res/sprites/Grenade.gif");
        flash = new FlashFX();
        grenadeSprite.setPostFX(flash);
        explosionAnim = new Animation(this, "game/res/sprites/Explosion1.gif", 1, 10, 50);
        explosionAnim.rowAnim("BOOM", 0);
        explosionAnim.setState("BOOM");
        grenadeSound = new Sound("game/res/sfx/GrenadeLaunch.wav", 0.45f);
        explosionSound = new Sound("game/res/sfx/Explosion1.wav", 0.5f);
    }

    public void draw(Graphics2D g2) {
        if (isActive && range > 10) {
            grenadeSprite.draw(g2);
        }
        if (range <= 10)
            explosionAnim.draw(g2);
    }

    public void fire(int aimX, int aimY) {
        super.fire(aimX, aimY);
        grenadeSound.play();
    }

    public void update() {
        if (range % 60 == 0)
            grenadeSprite.resetPostFX();
        if (range <= 10) {
            explosionSound.play();
            grenadeSound.stop();
            fragments[0].fire(-1, 1);
            fragments[1].fire(-1, 0);
            fragments[2].fire(-1, -1);
        }
        super.update();
    }
    public void reset() {
        isActive = false;
        t = 0;
        range = 100;
    }

    public Rectangle2D[] getBounds() {
        if (!isActive)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x - 1, y - 1, 8, 8);
        return bounds;
    }
}
