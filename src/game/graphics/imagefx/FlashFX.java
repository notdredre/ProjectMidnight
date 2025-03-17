package game.graphics.imagefx;

import java.awt.image.BufferedImage;

public class FlashFX implements ImageFX {
    private int t;
    private int numSteps;

    public FlashFX(int numSteps) {
        t = 0;
        this.numSteps = numSteps;
    }

    public FlashFX() {
        this(5);
    }

    public BufferedImage process(BufferedImage buffer) {
        if (t >= numSteps)
            return buffer;
        int bufferWidth = buffer.getWidth();
        int bufferHeight = buffer.getHeight();
        BufferedImage copy = new BufferedImage(bufferWidth, bufferHeight, BufferedImage.TYPE_INT_ARGB);
        int[] in = new int[bufferWidth * bufferHeight];
        buffer.getRGB(0, 0, bufferWidth, bufferHeight, in, 0, bufferWidth);

        for (int i = 0; i < bufferWidth * bufferHeight; i++) {
            int pixel = in[i];
            if (pixel == 0)
                continue;
            int[] comp = new int[4];
            comp[0] = pixel >> 24;
            comp[1] = pixel >> 16 & 255;
            comp[2] = pixel >> 8 & 255;
            comp[3] = pixel & 255;
            if (t <= numSteps / 2) {
                for (int j = 1; j < 4; j++) {
                    comp[j] = Math.clamp(comp[j] + 255 / numSteps, comp[j], 255);
                }
            } else {
                for (int j = 1; j < 4; j++) {
                    comp[j] = Math.clamp(comp[j] - 255 / numSteps, comp[j], 255);
                }
            }
            
        }
        copy.setRGB(0, 0, bufferWidth, bufferHeight, in, 0, bufferWidth);
        return copy;
    }

    public void reset () {
        t = 0;
    }
}
