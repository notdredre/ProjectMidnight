import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import game.GameManager;
import game.InputHandler;
import game.Player;
import game.Stage;

public class GamePanel extends JPanel implements Runnable {
    private GameManager gameManager;
    private BufferedImage frameBuffer;
    private Thread runThread;
    private long now, diff;

    public GamePanel() {
        gameManager = GameManager.getGameManager();
        frameBuffer = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Player p = new Player();
        addKeyListener(new InputHandler(p));
        Stage stage = new Stage();
        stage.initStage();
        p.setTicking(true);
        runThread = new Thread(this);
        now = System.currentTimeMillis();
    }

    public void startThread() {
        runThread.start();
    }

    public void run() {
        try {
            while (true) {
                requestFocus();
                diff = System.currentTimeMillis() - now;
                if (diff >= 10) {
                    now = System.currentTimeMillis();
                    gameManager.update();
                    Graphics2D f2 = (Graphics2D) frameBuffer.getGraphics();
                    f2.setColor(Color.BLACK);
                    f2.fillRect(0, 0, 1000, 1000);
                    gameManager.draw(f2);
                    f2.dispose();
                    Graphics2D g2 = (Graphics2D) getGraphics();
                    g2.drawImage(frameBuffer.getScaledInstance(2000, 2000, BufferedImage.SCALE_FAST), 0, 0, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}