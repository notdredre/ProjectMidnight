package game;

import java.util.ArrayList;
import java.util.Random;

public class Stage implements Updatable {
    private ArrayList<GameEntity> entities;
    private Random rand;
    private int t;
    private final int INTERVAL = 1000;
    private GameManager gameManager;

    public Stage() {
        entities = new ArrayList<>();
        t = 0;
        gameManager = GameManager.getGameManager();
        gameManager.addUpdatable(this);
        rand = new Random();
    }

    public void initStage() {
        int x = -50;
        for (int i = 0; i < 20; i++) {
            int y = (rand.nextInt(4) + 1) * 50;
            entities.add(new SimpleEnemy(x, y));
        }
        for (int i = 0; i < 5; i++) {
            int y = (rand.nextInt(4) + 1) * 50;
            entities.add(new BossEnemy(x, y));
        }
    }

    public void update() {
        t++;
        if (t % INTERVAL == 0) {
            int amount = rand.nextInt(7);
            for (int i = 0; i < amount; i++) {
                int next = rand.nextInt(entities.size());
                entities.get(next).setTicking(true);
            }
        }
    }
}
