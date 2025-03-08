package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.weapons.NormalWeapon;

public class SimpleEnemy extends Enemy {
    public SimpleEnemy() {
        this(0, 0);
    }

    public SimpleEnemy(int x, int y) {
        super(x, y);
        health = 10;
        weapon = new NormalWeapon(this);
        weapon.setAim(-1, 0);
    }

    public void draw(Graphics2D g2) {
        if (health <= 0)
            return;
        g2.setColor(Color.RED);
        g2.fillRect(x, y, 10, 10);
    }

    public void update() {
        int distX = playerX - x;
        int distY = playerY - y;
        double hyp = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
        int moveY = (int) Math.signum(distY / hyp);
        int moveX = (int) Math.signum(distX / hyp);
        y += dy;
        if (t % 100 <= 30) {
            dy = moveY;
            System.out.println(dy);
        } else
            dy = 0;
        if (t % 100 == 0) {
            weapon.setAim(-1, 0);
            weapon.fire();
        }
    }

    public Rectangle2D[] getBounds() {
        if (health <= 0)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 10, 10);
        return bounds;
    }
}
