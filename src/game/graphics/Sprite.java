package game.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.Drawable;
import game.GameEntity;
import game.graphics.imagefx.ImageFX;

public class Sprite implements Drawable {
    private GameEntity owner;
    private String path;
    private BufferedImage spriteImage;
    private int x, y;
    private ImageFX post;

    public Sprite(GameEntity owner, String path) {
        this.owner = owner;
        this.path = path;
        post = null;
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

    public void setPostFX(ImageFX post) {
        this.post = post;
    }

    public void resetPostFX() {
        post.reset();
    }

    public void draw(Graphics2D g2) {
        x = owner.getX();
        y = owner.getY();
        BufferedImage toDraw = spriteImage;
        if (post!= null)
            toDraw = post.process(toDraw);
        g2.drawImage(toDraw, x, y, null);
    }
}
