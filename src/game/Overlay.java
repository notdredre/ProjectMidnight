package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Overlay implements Drawable {
    private GameManager gameManager;
    private String scoreText;

    public Overlay() {
        gameManager = GameManager.getGameManager();
    }

    public void draw(Graphics2D g2) {
        scoreText = "Score: " + gameManager.getScore();
        g2.setColor(Color.WHITE);
        g2.drawString(scoreText, 25, 400);
    }
}
