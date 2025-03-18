package game.weapons;

import java.awt.Graphics2D;
import java.awt.Color;
import game.GameEntity;

public class NormalEnemyProjectile extends EnemyProjectile {
    public NormalEnemyProjectile(GameEntity source) {
        super(source);
        damage = 2;
        range = 500;
    }

    public void reset() {
        super.reset();
        range = 500;
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.WHITE);
            g2.drawOval(x, y, 5, 5);
        }
    }
}
