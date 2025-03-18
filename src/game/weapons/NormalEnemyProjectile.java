package game.weapons;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import game.GameEntity;
import game.sound.Sound;

public class NormalEnemyProjectile extends EnemyProjectile {
    private Sound fireSound;
    public NormalEnemyProjectile(GameEntity source, int offsetX, int offsetY) {
        super(source, offsetX, offsetY);
        damage = 2;
        range = 500;
        fireSound = new Sound("src/game/res/sfx/Normal Weapon.wav", 0.3f);
    }

    public void fire(int aimX, int aimY) {
        super.fire(aimX, aimY);
        fireSound.play();
    }

    public void reset() {
        isActive = false;
        t = 0;
        range = 500;
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.WHITE);
            g2.drawOval(x, y, 5, 5);
        }
    }
    
    public Rectangle2D[] getBounds() {
        if (!isActive)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 5, 5);
        return bounds;
    }
}
