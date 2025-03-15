package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GameEntity;

public class NormalProjectile extends Projectile {
    
    public NormalProjectile(GameEntity source) {
        super(source);
        damage = 5;
        range = 500;
    }

    public void reset() {
        super.reset();
        range = 500;
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.WHITE);
            g2.drawOval(x, y, 5, 5);
        }
    }
}
