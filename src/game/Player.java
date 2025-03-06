package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends GameEntity {
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, 10, 10);
    }

    public void update() {
        super.update();
        x += 1;
    }
}
