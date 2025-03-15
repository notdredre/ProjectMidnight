package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.actions.BossMovesClose;
import game.actions.BossMovesFar;
import game.actions.MoveSet;
import game.weapons.NormalWeapon;
import game.weapons.Weapon;

public class BossEnemy extends Enemy {
    private MoveSet moves;

    public BossEnemy() {
        this(0, 0);
    }

    public BossEnemy(int x, int y) {
        super(x, y);
        health = 30;
        weapons = new Weapon[8];
        for (int i = 0; i < 8; i++)
            weapons[i] = new NormalWeapon(this);
        weapons[0].setAim(-1, 0);
        weapons[1].setAim(-1, 1);
        weapons[2].setAim(0, 1);
        weapons[3].setAim(1, 1);
        weapons[4].setAim(1, 0);
        weapons[5].setAim(1, -1);
        weapons[6].setAim(0, -1);
        weapons[7].setAim(-1, -1);
        moves = new BossMovesClose(this);
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
        weapons[0].fire();
    }

    public void specialAttack() {
        for (Weapon w : weapons)
            w.fire();
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
