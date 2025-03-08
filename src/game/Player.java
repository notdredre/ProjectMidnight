package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

import game.weapons.NormalWeapon;
import game.weapons.Weapon;

public class Player extends DamageEntity {
    private Weapon weapon;

    public Player() {
        health = 20;
        weapon = new NormalWeapon(this);
        weapon.setAim(1, 0);
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, 10, 10);
    }

    public void update() {
        x += dx;
        y += dy;   
    }

    public void handleKeyInput(Collection<KeyEvent> events) {
        dx = dy = 0;

        for (KeyEvent e : events) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    dy = -1;
                    break;
                case KeyEvent.VK_DOWN:
                    dy = 1;
                    break;
                case KeyEvent.VK_LEFT:
                    dx = -1;
                    break;
                case KeyEvent.VK_RIGHT:
                    dx = 1;
                    break;
                case KeyEvent.VK_Z:
                    weapon.fire();
            }
        }
    }

    public Rectangle2D[] getBounds() {
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 10, 10);
        return bounds;
    }
}