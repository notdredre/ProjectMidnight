package game.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.Drawable;
import game.GameEntity;

public class Sprite implements Drawable {
    private GameEntity owner;
    private String path;
    private BufferedImage spriteImage;
    private int x, y;

    public Sprite(GameEntity owner, String path) {
        this.owner = owner;
        this.path = path;
        loadSprite();
    }

    private void loadSprite() {
        try {
            File in = new File(path);
            spriteImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        x = owner.getX();
        y = owner.getY();
        g2.drawImage(spriteImage, x, y, null);
    }
}
