package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GameEntity;

public class Projectile extends GameEntity {
    private int damage, range;

    public Projectile(int x, int y, int dx, int dy) {
        super();
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
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
}
