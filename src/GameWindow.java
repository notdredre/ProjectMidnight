import javax.swing.JFrame;

public class GameWindow extends JFrame {
    private GamePanel gamePanel;

    public GameWindow() {
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);
        gamePanel.startThread();
    }
}