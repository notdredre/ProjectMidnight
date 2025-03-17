package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GameEntity;
import game.graphics.Animation;
import game.graphics.Sprite;

public class SplittingProjectile extends EnemyProjectile {
    private Projectile[] fragments;
    private final int NUM_FRAGS = 3;
    private Sprite grenadeSprite;
    private Animation explosionAnim;

    public SplittingProjectile(GameEntity source) {
        super(source);
        damage = 20;
        range = 110;
        fragments = new NormalEnemyProjectile[NUM_FRAGS];
        for (int i = 0; i < NUM_FRAGS; i++)
            fragments[i] = new NormalEnemyProjectile(this);
        grenadeSprite = new Sprite(this, "src/game/res/sprites/Grenade.gif");
        explosionAnim = new Animation(this, "src/game/res/sprites/Explosion1.gif", 1, 10, 50);
        explosionAnim.rowAnim("BOOM", 0);
        explosionAnim.setState("BOOM");
    }

    public void draw(Graphics2D g2) {
        if (isActive && range > 10) {
            grenadeSprite.draw(g2);
        }
        if (range <= 10)
            explosionAnim.draw(g2);
    }

    public void update() {
        if (range <= 10) {
            fragments[0].fire(-1, 1);
            fragments[1].fire(-1, 0);
            fragments[2].fire(-1, -1);
        }
        super.update();
    }
    public void reset() {
        super.reset();
        range = 100;
    }
}
