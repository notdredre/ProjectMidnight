package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameManager implements Drawable, Updatable {
    private static GameManager instance = null;
    private ArrayList<GameEntity> gameEntities;
    private ArrayList<Updatable> updatables;

    private GameManager() {
        gameEntities = new ArrayList<>();
        updatables = new ArrayList<>();
    }

    public static GameManager getGameManager() {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    public void addGameEntity(GameEntity ge) {
        gameEntities.add(ge);
    }

    public void addUpdatable(Updatable u) {
        updatables.add(u);
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
        
        for (GameEntity g : gameEntities) {
            g.update();
        }
    }

    public void draw(Graphics2D g2) {
        for (GameEntity g : gameEntities) {
            g.draw(g2);
        }
    }
}
