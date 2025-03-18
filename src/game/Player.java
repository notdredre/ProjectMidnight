package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

import game.graphics.Animation;
import game.graphics.imagefx.FlashFX;
import game.graphics.imagefx.ImageFX;
import game.sound.Sound;
import game.weapons.NormalWeapon;
import game.weapons.SpecialWeapon;
import game.weapons.Weapon;

public class Player extends DamageEntity {
    private Weapon weapon;
    private NormalWeapon normal;
    private SpecialWeapon special;
    private int charge;
    private Sound chargeSound, lowHealthSound, background;
    private boolean discharged;
    private final int CHARGE_LIMIT = 600;
    private Animation anim;

    public Player(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Player() {
        health = 20;
        normal = new NormalWeapon(this);
        special = new SpecialWeapon(this);
        weapon = normal;
        weapon.setAim(1, 0);
        discharged = false;
        chargeSound = new Sound("src/game/res/sfx/Charge Ready.wav", 0.65f);
        lowHealthSound = new Sound("src/game/res/sfx/Health Low.wav", true, 0.7f);
        anim = new Animation(this, "src/game/res/sprites/player test.gif", 5, 5); 
        anim = new Animation(this, "src/game/res/sprites/PlayerSheet.gif", 6, 6, 40, true);
        anim.rowAnim("Forward", 0);
        anim.rowAnim("ForwardLow", 1);
        anim.rowAnim("Down", 2);
        anim.rowAnim("DownLow", 3);
        anim.rowAnim("Up", 4);
        anim.rowAnim("UpLow", 5);
        anim.setState("Forward");
        background = new Sound("src/game/res/sfx/Background.wav", true, 0.8f);
        background.play();
        processor = anim;
    }

    public void draw(Graphics2D g2) {
        anim.draw(g2);
    }

    public void update() {
        charge = gameManager.getCharge();
        if (dy > 0) {
            anim.setState("Down");
        } else if (dy < 0) {
            anim.setState("Up");
        } else {
            anim.setState("Forward");
        }
        x += dx;
        y += dy;
        if (x >= 410)
            x = 410;
        if (x <= 0)
            x = 0;
        if (y >= 410)
            y = 410;
        if (y <= 0)
            y = 0;
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

    public int getHealth() {
        return Math.clamp(health, 0, 200);
    }
    
    public void damage(int damage) {
        super.damage(damage);
        if (health <= 5) {
            anim.setModifier("Low");
            if (!lowHealthSound.isPlaying())
                lowHealthSound.play();
        }
    }
    
    public Weapon getActiveWeapon() {
        return weapon;
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
                        gameManager.discharge();
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
        bounds[0] = new Rectangle2D.Double(x + 6, y + 12, 20, 7);
        return bounds;
    }
}