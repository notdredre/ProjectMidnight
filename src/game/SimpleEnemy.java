package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.actions.MoveSet;
import game.actions.SimpleEnemyMoves;
import game.graphics.Sprite;
import game.weapons.NormalEnemyWeapon;
import game.weapons.Weapon;

public class SimpleEnemy extends Enemy {
    private MoveSet moves;
    private Sprite enemySprite;

    public SimpleEnemy() {
        this(0, 0);
    }

    public SimpleEnemy(int x, int y) {
        super(x, y);
        health = 10;
        weapons = new Weapon[1];
        weapons[0] = new NormalEnemyWeapon(this);
        weapons[0].setAim(-1, 0);
        moves = new SimpleEnemyMoves(this);
        enemySprite = new Sprite(this, "src/game/res/sprites/Enemy.gif");
    }

    public void draw(Graphics2D g2) {
        if (health <= 0) {
            explosionAnim.draw(g2);
            return;
        }
        enemySprite.draw(g2);
    }

    public void update() {
        x += dx;
        y += dy;
       moves.act(t);
    }

    public void attack() {
        weapons[0].fire();
    }
    
    public Rectangle2D[] getBounds() {
        if (health <= 0)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 10, 10);
        return bounds;
    }
}
