package game.graphics.imagefx;

import java.awt.image.BufferedImage;

public class DesaturationFX implements ImageFX {
    private int t;
    private int numSteps;
    private float amount;

    public DesaturationFX(int numSteps, float amount) {
        t = -1;
        this.numSteps = numSteps;
        this.amount = amount;
    }

    public DesaturationFX() {
        this(100, 0.8f);
    }

    public BufferedImage process(BufferedImage buffer) {
        t = Math.clamp(t + 1, 0, numSteps);
        int bufferWidth = buffer.getWidth();
        int bufferHeight = buffer.getHeight();
        int[] in = new int[bufferWidth * bufferHeight];
        buffer.getRGB(0, 0, bufferWidth, bufferHeight, in, 0, bufferHeight);
        for (int i = 0; i < bufferWidth * bufferHeight; i++) {
            int pixel = in[i];
            if (pixel == 0)
                continue;
            int comp[] = new int[4];
            for (int j = 0; j < 4; j++) {
                comp[j] = pixel >> ((3 - j) * 8) & 255;
            }
            if (comp[1] == comp[2] && comp[2] == comp[3])
                continue;
            int grey = 0;
            for (int j = 1; j < 4; j++) {
                grey += comp[j];
            }
            grey /= 3;
            int diff[] = new int[3];
            for (int j = 0; j < 3; j++) {
                diff[j] = Math.round((grey - comp[j + 1]) * t / numSteps * amount);
                comp[j + 1] += diff[j];
            }

            pixel = comp[3] | comp[2] << 8 | comp[1] << 16 | comp[0] << 24;
            in[i] = pixel;
        }
        buffer.setRGB(0, 0, bufferWidth, bufferHeight, in, 0, bufferWidth);
        return buffer;
    }

    public void reset() {
        t = -1;
    }
}
