package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Overlay implements Drawable {
    private GameManager gameManager;
    private String scoreText, healthText, chargeText;

    public Overlay() {
        gameManager = GameManager.getGameManager();
    }

    public void draw(Graphics2D g2) {
        healthText = "Health: ";
        int healthAmount = gameManager.getHealth() / 2;
        for (int i = 0; i < healthAmount; i++) {
            healthText += "|";
        }
        
        scoreText = "Score: " + gameManager.getScore();
        chargeText = "Charge: ";
        int chargeAmount = gameManager.getCharge() / 100;
        for (int i = 0; i < chargeAmount; i++) {
            chargeText += "|";
        }
        
        g2.setColor(Color.WHITE);
        g2.drawString(healthText, 25,350);
        g2.drawString(scoreText, 25, 375);
        g2.drawString(chargeText, 25, 400);
    }
}
