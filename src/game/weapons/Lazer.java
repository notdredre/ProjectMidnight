package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.GameEntity;

public class Lazer extends Projectile {
    public Lazer(GameEntity source) {
        super(source);
        damage = 15;
        range = 50;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.PINK);
            g2.fillRect(x, y, 1000, 10);
        }
    }

    public void reset() {
        super.reset();
        range = 50;
    }

    public Rectangle2D[] getBounds() {
        if (!isActive)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 1000, 10);
        return bounds;
    }
}
