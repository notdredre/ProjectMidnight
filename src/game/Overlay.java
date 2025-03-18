package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Overlay implements Drawable {
    private GameManager gameManager;
    private String scoreText, healthText, chargeText;
    int result;

    public Overlay() {
        gameManager = GameManager.getGameManager();
        result = -1;
    }

    public void setResult(int result) {
        this.result = result;
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
        if (result == 0) {
            String resultText = "GAME OVER";
            String finalScore = "Final Score: " + gameManager.getScore();
            g2.drawString(resultText, 200, 210);
            g2.drawString(finalScore, 200, 230);
        }
        if (result == 1) {
            String resultText = "YOU ESCAPED!";
            String finalScore = "Final Score: " + gameManager.getScore();
            g2.drawString(resultText, 200, 210);
            g2.drawString(finalScore, 200, 230);
        }
    }
}
