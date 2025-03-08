package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

import game.CollisionChecker;
import game.DamageEntity;
import game.GameEntity;

public class Projectile extends GameEntity implements CollisionChecker {
    private int damage, range;
    private GameEntity source;

    public Projectile(int x, int y, int dx, int dy, GameEntity source) {
        super();
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.source = source;
        damage = 5;
        range = 500;
    }

    public void draw(Graphics2D g2) {
        if (range <= 0)
            return;
        g2.setColor(Color.WHITE);
        g2.drawOval(x, y, 5, 5);
    }

    public void update() {
        if (range <= 0)
            return;
        x += dx;
        y += dy;
        range--;
    }

    public void checkCollisions(Collection<DamageEntity> damageEntities) {
        for (DamageEntity d : damageEntities) {
            if (!d.equals(source)) {
                for (Rectangle2D r : d.getBounds()) {
                    for (Rectangle2D r2 : getBounds()) {
                        if (r.contains(r2))
                            d.damage(damage);
                    }
                }
            }
        }
    }

    public Rectangle2D[] getBounds() {
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 5, 5);
        return bounds;
    }
}
