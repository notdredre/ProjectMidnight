package game;

import java.awt.Color;
import java.awt.Graphics2D;

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
        g2.setColor(Color.RED);
        g2.fillRect(x, y, 10, 10);
    }

    public void update() {
        super.update();
        y = playerY;
        if (t % 100 == 0)
            weapon.fire();
    }
}
