package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GameEntity;
import game.graphics.Sprite;

public class SplittingProjectile extends EnemyProjectile {
    private Projectile[] fragments;
    private final int NUM_FRAGS = 3;
    private Sprite grenadeSprite;

    public SplittingProjectile(GameEntity source) {
        super(source);
        damage = 20;
        range = 100;
        fragments = new NormalEnemyProjectile[NUM_FRAGS];
        for (int i = 0; i < NUM_FRAGS; i++)
            fragments[i] = new NormalEnemyProjectile(this);
        grenadeSprite = new Sprite(this, "src/game/res/sprites/Grenade.gif");
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            grenadeSprite.draw(g2);
        }
    }

    public void update() {
        if (range <= 0) {
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
