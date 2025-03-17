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
        health = 30;
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
        bossSprite.draw(g2);
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        moves.act(t);
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
        return new Rectangle2D[0];
    }
    
}
