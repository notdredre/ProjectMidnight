package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Vector;

public class GameManager implements Drawable, Updatable {
    private static GameManager instance = null;
    private GameEntity player;
    private Vector<GameEntity> gameEntities;
    private ArrayList<DamageEntity> damageEntities;
    private ArrayList<Updatable> updatables;
    private ArrayList<TargetPlayer> targetPlayers;
    private Vector<CollisionChecker> collisionCheckers;

    private GameManager() {
        gameEntities = new Vector<>();
        updatables = new ArrayList<>();
        targetPlayers = new ArrayList<>();
        damageEntities = new ArrayList<>();
        collisionCheckers = new Vector<>();
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
        if (ge instanceof DamageEntity)
            damageEntities.add((DamageEntity) ge);
        if (ge instanceof CollisionChecker)
            collisionCheckers.add((CollisionChecker) ge);
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
        
        for (GameEntity ge : gameEntities) {
            ge.tick();
        }

        for (CollisionChecker cc : collisionCheckers) {
            cc.checkCollisions(damageEntities);
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
