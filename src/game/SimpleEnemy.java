package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.actions.MoveSet;
import game.actions.SimpleEnemyMoves;
import game.weapons.NormalWeapon;

public class SimpleEnemy extends Enemy {
    private MoveSet moves;

    public SimpleEnemy() {
        this(0, 0);
    }

    public SimpleEnemy(int x, int y) {
        super(x, y);
        health = 10;
        weapon = new NormalWeapon(this);
        weapon.setAim(-1, 0);
        moves = new SimpleEnemyMoves(this);
    }

    public void draw(Graphics2D g2) {
        if (health <= 0)
            return;
        g2.setColor(Color.RED);
        g2.fillRect(x, y, 10, 10);
    }

    public void update() {
        x += dx;
        y += dy;
       moves.act(t);
    }

    public void attack() {
        weapon.fire();
    }
    
    public Rectangle2D[] getBounds() {
        if (health <= 0)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 10, 10);
        return bounds;
    }
}
