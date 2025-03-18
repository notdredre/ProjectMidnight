package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import game.actions.BossMovesFar;
import game.actions.MoveSet;
import game.graphics.Sprite;
import game.weapons.EnemyGrenadeWeapon;
import game.weapons.NormalEnemyWeapon;
import game.weapons.Weapon;

public class BossEnemy extends Enemy {
    private MoveSet moves;
    private Sprite bossSprite;

    public BossEnemy() {
        this(0, 0);
    }

    public BossEnemy(int x, int y) {
        super(x, y);
        health = 60;
        bossSprite = new Sprite(this, "src/game/res/sprites/BossEnemy.gif");
        weapons = new Weapon[8];
        weapons[0] = new EnemyGrenadeWeapon(this);
        for (int i = 1; i < 8; i++)
            weapons[i] = new NormalEnemyWeapon(this);
        weapons[0].setAim(-1, 0);
        weapons[1].setAim(-1, 1);
        weapons[2].setAim(0, 1);
        weapons[3].setAim(1, 1);
        weapons[4].setAim(1, 0);
        weapons[5].setAim(1, -1);
        weapons[6].setAim(0, -1);
        weapons[7].setAim(-1, -1);
        moves = new BossMovesFar(this);
        value = 250;
    }

    public void changeMoveSet(MoveSet moves) {
        this.moves = moves;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (health <= 0) {
            explosionAnim.draw(g2);
            return;
        }
        bossSprite.draw(g2);
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        if (t <= 2000)
            moves.act(t);
        else
            moveUp();
    }

    public void damage(int damage) {
        super.damage(damage);
        if (health <= 0)
            gameManager.updateScore(value);
    }

    public void basicAttack() {
        float diffX = playerX - x;
        float diffY = playerY - y;
        float hyp = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        int DX = Math.round(diffX / hyp);
        int DY = Math.round(diffY / hyp);
        weapons[0].setAim(DX, DY);
        weapons[0].fire();
        weapons[0].setAim(-1, 0);
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
        if (health <= 0)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x + 15, y + 10, 25, 28);
        return bounds;
    }
    
}
