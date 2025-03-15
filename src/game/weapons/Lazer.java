package game.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.GameEntity;
import game.sound.Sound;

public class Lazer extends Projectile {
    private Sound fireSound;

    public Lazer(GameEntity source) {
        super(source);
        damage = 15;
        range = 50;
        fireSound = new Sound("src/game/res/sfx/Lazer.wav", 0.7f);
    }

    @Override
    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.PINK);
            g2.fillRect(x, y, 1000, 10);
        }
    }

    public void fire(int aimX, int aimY) {
        if (!fireSound.isPlaying()) {
            fireSound.play();
            System.out.println("HERE");
        }
            
        super.fire(aimX, aimY);
    }

    public void update() {
        if (!isActive)
            fireSound.stop();
        super.update();
    }

    public void reset() {
        super.reset();
        range = 50;
    }

    public Rectangle2D[] getBounds() {
        if (!isActive)
            return new Rectangle2D[0];
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 1000, 10);
        return bounds;
    }
}
