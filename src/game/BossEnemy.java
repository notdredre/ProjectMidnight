package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.actions.BossMovesFar;
import game.actions.MoveSet;
import game.weapons.NormalWeapon;

public class BossEnemy extends Enemy {
    private MoveSet moves;

    public BossEnemy() {
        this(0, 0);
    }

    public BossEnemy(int x, int y) {
        super(x, y);
        health = 30;
        weapon = new NormalWeapon(this);
        weapon.setAim(-1, 0);
        moves = new BossMovesFar(this);
    }

    public void changeMoveSet(MoveSet moves) {
        this.moves = moves;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.fillOval(x, y, 15, 15);
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        moves.act(t);
    }

    public void basicAttack() {
        weapon.fire();
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    @Override
    public Rectangle2D[] getBounds() {
        return new Rectangle2D[0];
    }
    
}
