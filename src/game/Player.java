package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

import game.graphics.Animation;
import game.sound.Sound;
import game.weapons.NormalWeapon;
import game.weapons.SpecialWeapon;
import game.weapons.Weapon;

public class Player extends DamageEntity {
    private Weapon weapon;
    private NormalWeapon normal;
    private SpecialWeapon special;
    private int charge;
    private Sound chargeSound, lowHealthSound;
    private boolean discharged;
    private final int CHARGE_LIMIT = 500;
    private Animation anim;

    public Player() {
        health = 20;
        normal = new NormalWeapon(this);
        special = new SpecialWeapon(this);
        weapon = normal;
        weapon.setAim(1, 0);
        charge = 0;
        discharged = false;
        chargeSound = new Sound("src/game/res/sfx/Charge Ready.wav", 0.7f);
        lowHealthSound = new Sound("src/game/res/sfx/Health Low.wav", true, 0.7f);
        anim = new Animation(this, "src/game/res/sprites/PlayerSheet.gif", 6, 6, 40, true);
        anim.rowAnim("Forward", 0);
        anim.rowAnim("ForwardLow", 1);
        anim.rowAnim("Down", 2);
        anim.rowAnim("DownLow", 3);
        anim.rowAnim("Up", 4);
        anim.rowAnim("UpLow", 5);
        anim.setState("Forward");
    }

    public void draw(Graphics2D g2) {
        anim.draw(g2);
    }

    public void update() {
        if (dy > 0) {
            anim.setState("Down");
        } else if (dy < 0) {
            anim.setState("Up");
        } else {
            anim.setState("Forward");
        }
        x += dx;
        y += dy;
        if (weapon.equals(normal) && charge < CHARGE_LIMIT)
            charge++;
        if (charge <= 0 && weapon.equals(special)) {
            weapon = normal;
            discharged = false;
        }            
        if (charge == CHARGE_LIMIT && !discharged) {
            discharged = true;
            chargeSound.play();
        }
            
    }

    public void damage(int damage) {
        super.damage(damage);
        if (health <= 5) {
            anim.setModifier("Low");
            if (!lowHealthSound.isPlaying())
                lowHealthSound.play();
        }
    }
    
    public void handleKeyInput(Collection<KeyEvent> events) {
        dx = dy = 0;
        if (health <= 0)
            return;

        for (KeyEvent e : events) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    dy = -1;
                    break;
                case KeyEvent.VK_DOWN:
                    dy = 1;
                    break;
                case KeyEvent.VK_LEFT:
                    dx = -1;
                    break;
                case KeyEvent.VK_RIGHT:
                    dx = 1;
                    break;
                case KeyEvent.VK_Z:
                    if (charge >= 0 && weapon.equals(special))
                        charge--;
                    weapon.fire();
                    break;
                case KeyEvent.VK_X:
                    if (charge >= CHARGE_LIMIT)
                        weapon = special;
            }
        }
    }

    public Rectangle2D[] getBounds() {
        Rectangle2D[] bounds = new Rectangle2D[1];
        bounds[0] = new Rectangle2D.Double(x, y, 10, 10);
        return bounds;
    }
}