package game.graphics.imagefx;

import java.awt.image.BufferedImage;

public class DesaturationFX implements ImageFX {
    private int t;
    private int numSteps;

    public DesaturationFX(int numSteps) {
        t = -1;
        this.numSteps = numSteps;
    }

    public DesaturationFX() {
        this(20);
    }

    public BufferedImage process(BufferedImage buffer) {
        t = Math.clamp(t + 1, 0, numSteps);
        int bufferWidth = buffer.getWidth();
        int bufferHeight = buffer.getHeight();
        BufferedImage copy = new BufferedImage(bufferWidth, bufferHeight, BufferedImage.TYPE_INT_ARGB);
        int[] in = new int[bufferWidth * bufferHeight];
        buffer.getRGB(0, 0, bufferWidth, bufferHeight, in, 0, bufferHeight);
        for (int i = 0; i < bufferWidth * bufferHeight; i++) {
            int pixel = in[i];
            int comp[] = new int[4];
            for (int j = 0; j < 4; j++) {
                comp[j] = pixel >> ((3 - j) * 8) & 255;
            }
            int grey = 0;
            for (int j = 1; j < 4; j++) {
                grey += comp[j];
            }
            grey /= 3;
            int diff[] = new int[3];
            for (int j = 0; j < 3; j++) {
                diff[j] = (grey - comp[j + 1]) * t / numSteps;
                comp[j + 1] += diff[j];
            }

            pixel = comp[3] | comp[2] << 8 | comp[1] << 16 | comp[0] << 24;
            in[i] = pixel;
        }
        copy.setRGB(0, 0, bufferWidth, bufferHeight, in, 0, bufferWidth);
        return copy;
    }

    public void reset() {
        t = -1;
    }
}
