package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GameEntity;

public class SplittingProjectile extends Projectile {
    private Projectile[] fragments;
    private final int NUM_FRAGS = 3;

    public SplittingProjectile(GameEntity source) {
        super(source);
        damage = 20;
        range = 100;
        fragments = new NormalProjectile[NUM_FRAGS];
        for (int i = 0; i < NUM_FRAGS; i++)
            fragments[i] = new NormalProjectile(this);
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.MAGENTA);
            g2.fillOval(x, y, 10, 10);
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
