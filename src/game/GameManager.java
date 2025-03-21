package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Vector;

import game.weapons.NormalWeapon;

public class GameManager implements Drawable, Updatable {
    private static GameManager instance = null;
    private Player player;
    private Vector<GameEntity> gameEntities;
    private ArrayList<DamageEntity> damageEntities;
    private ArrayList<Updatable> updatables;
    private ArrayList<TargetPlayer> targetPlayers;
    private Vector<CollisionChecker> collisionCheckers;
    private int score, charge;
    private final int CHARGE_LIMIT = 600;

    private GameManager() {
        gameEntities = new Vector<>();
        updatables = new ArrayList<>();
        targetPlayers = new ArrayList<>();
        damageEntities = new ArrayList<>();
        collisionCheckers = new Vector<>();
        score = 0;
        charge = 0;
    }

    public static GameManager getGameManager() {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    public void addGameEntity(GameEntity ge) {
        gameEntities.add(ge);
        if (ge instanceof Player)
            player = (Player) ge;
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

    public boolean isFinished() {
        for (DamageEntity d : damageEntities) {
            if (d.equals(player))
                continue;
            if (d.getHealth() > 0 && d.getY() > 0)
                return false;
        }
        return true;
    }

    public void updateScore(int value) {
        score += value;
        if (player.getActiveWeapon() instanceof NormalWeapon)
            charge = Math.clamp(value + charge, 0, CHARGE_LIMIT);
    }

    public int getCharge() {
        return charge;
    }

    public void discharge() {
        charge--;
    }
    
    public int getScore() {
        return score;
    }

    public int getHealth() {
        return player.getHealth();
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
