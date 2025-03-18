package game.weapons;

import java.awt.Graphics2D;
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
