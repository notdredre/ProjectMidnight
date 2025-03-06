package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputHandler implements Updatable, KeyListener {
    private Player player;
    private GameManager gameManager;
    private ArrayList<KeyEvent> events;
    private boolean lock;

    public InputHandler(Player player) {
        this.player = player;
        gameManager = GameManager.getGameManager();
        gameManager.addUpdatable(this);
        events = new ArrayList<>();
        lock = false;
    }

    public void update() {
        if (lock)
            return;
        player.handleKeyInput(events);
    }

    public void keyPressed(KeyEvent e) {
        lock = true;
        for (KeyEvent p : events) {
            if (p.getKeyCode() == e.getKeyCode())
                return;
        }
        events.add(e);
        lock = false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        lock = true;
        ArrayList<KeyEvent> toRemove = new ArrayList<>();
        for (KeyEvent p : events) {
            if (e.getKeyCode() == p.getKeyCode())
                toRemove.add(p);
        }

        for (KeyEvent r : toRemove) {
            events.remove(r);
        }
        lock = false;
    }
}