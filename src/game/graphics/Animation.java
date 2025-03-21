package game.graphics;

import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import game.Drawable;
import game.GameEntity;
import game.graphics.imagefx.ImageFX;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation implements Drawable, PostFX{
    private HashMap<String, ArrayList<BufferedImage>> animMap;
    private int step, x, y, numCols, numRows;
    private long now, diff, target;
    private GameEntity owner;
    private String path, currentState, modifier;
    private ImageFX post;
    private boolean loop;

    public Animation(GameEntity owner, String path, int numRows, int numCols, long target, boolean loop) {
        animMap = new HashMap<>();
        this.target = target;
        step = -1;
        now = System.currentTimeMillis();
        this.owner = owner;
        this.path = path;
        this.numCols = numCols;
        this.numRows = numRows;
        modifier = "";
        this.loop = loop;
        post = null;
    }

    public Animation(GameEntity owner, String path, int numRows, int numCols, long target) {
        this(owner, path, numRows, numCols, target, false);
    }

    public Animation(GameEntity owner, String path, int numRows, int numCols) {
        this(owner, path, numRows, numCols, 33, false);
    }

    public void setState(String state) {
        currentState = state + modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setPostFX(ImageFX post) {
        this.post = post;
    }

    public void rowAnim(String stateName, int row) {
        ArrayList<BufferedImage> string = new ArrayList<>();
        try {
            BufferedImage in = ImageIO.read(getClass().getClassLoader().getResource(path));
            int width = in.getWidth() / numCols;
            int height = in.getHeight() / numRows;
            for (int i = 0; i < numCols; i++) {
                BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D f2 = (Graphics2D) frame.getGraphics();
                f2.drawImage(in, 0, 0, width, height, i * width, row * height, i * width + width, row * height + height, null);
                string.add(frame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        animMap.put(stateName, string);
    }

    public BufferedImage getCurrentFrame() {
        return animMap.get(currentState).get(step);
    }

    public void draw(Graphics2D g2) {
        ArrayList<BufferedImage> string = animMap.get(currentState);
        diff = System.currentTimeMillis() - now;
        if (diff >= target) {
            if (loop)
                step = (step + 1) % string.size();
            else
                step++;
            now = System.currentTimeMillis();
        }
        x = owner.getX();
        y = owner.getY();
        if (step >= string.size())
            return;
        BufferedImage toDraw = string.get(step);
        if (post != null)
            toDraw = post.process(toDraw);
        g2.drawImage(toDraw, x, y, null);
    }
}
