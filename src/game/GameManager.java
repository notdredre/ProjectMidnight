package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class GameManager implements Drawable, Updatable {
    private static GameManager instance = null;
    private GameEntity player;
    private Vector<GameEntity> gameEntities;
    private ArrayList<Updatable> updatables;
    private ArrayList<TargetPlayer> targetPlayers;

    private GameManager() {
        gameEntities = new Vector<>();
        updatables = new ArrayList<>();
        targetPlayers = new ArrayList<>();
    }

    public static GameManager getGameManager() {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    public void addGameEntity(GameEntity ge) {
        gameEntities.add(ge);
        if (ge instanceof Player)
            player = ge;
    }

    public void addUpdatable(Updatable u) {
        updatables.add(u);
    }

    public void addTargetPlayer(TargetPlayer t) {
        targetPlayers.add(t);
    }

    public void startTicking() {
        for (GameEntity g : gameEntities) {
            g.setTicking(true);
        }
    }

    public void stopTicking() {
        for (GameEntity g : gameEntities) {
            g.setTicking(false);
        }
    }

    public void update() {
        for (Updatable u : updatables) {
            u.update();
        }
        
        Enumeration<GameEntity> ge = gameEntities.elements();
        while (ge.hasMoreElements()) {
            ge.nextElement().update();
        }

        for (TargetPlayer t : targetPlayers) {
            t.updatePlayerLocation(player.getX(), player.getY());
        }
    }

    public void draw(Graphics2D g2) {
        for (GameEntity g : gameEntities) {
            g.draw(g2);
        }
    }
}
