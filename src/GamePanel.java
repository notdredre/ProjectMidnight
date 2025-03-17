import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import game.GameManager;
import game.InputHandler;
import game.Overlay;
import game.Player;
import game.Stage;
import game.graphics.imagefx.DesaturationFX;
import game.graphics.imagefx.ImageFX;

public class GamePanel extends JPanel implements Runnable {
    private GameManager gameManager;
    private BufferedImage frameBuffer;
    private Thread runThread;
    private long now, diff;
    private ImageFX desat;
    private Player player;
    private Overlay overlay;

    public GamePanel() {
        gameManager = GameManager.getGameManager();
        frameBuffer = new BufferedImage(450, 450, BufferedImage.TYPE_INT_ARGB);
        player = new Player();
        addKeyListener(new InputHandler(player));
        Stage stage = new Stage();
        overlay = new Overlay();
        stage.initStage();
        player.setTicking(true);
        runThread = new Thread(this);
        now = System.currentTimeMillis();
        desat = new DesaturationFX();
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
                    f2.fillRect(0, 0, 450, 450);
                    gameManager.draw(f2);
                    overlay.draw(f2);
                    f2.dispose();
                    if (player.getHealth() <= 5)
                        frameBuffer = desat.process(frameBuffer); 
                    Graphics2D g2 = (Graphics2D) getGraphics();
                    g2.drawImage(frameBuffer.getScaledInstance(1000, 1000, BufferedImage.SCALE_FAST), 0, 0, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}