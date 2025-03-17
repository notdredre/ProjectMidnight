package game.graphics.imagefx;

import java.awt.image.BufferedImage;

public interface ImageFX {
    public abstract BufferedImage process(BufferedImage buffer);
    public abstract void reset();
}
