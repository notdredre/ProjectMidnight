package game.sound;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip;
    private boolean loop;

    public Sound(String path, boolean loop) {
        File target = new File(path);
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(target);
            clip = AudioSystem.getClip();
            clip.open(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.loop = loop;
    }

    public Sound(String path) {
        this(path, false);
    }

    public void play() {
        if (clip != null) {
			clip.setFramePosition(0);
			if (loop)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.start();
		}
    }

    public void load(String path) {
        File target = new File(path);
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(target);
            clip = AudioSystem.getClip();
            clip.open(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLooping(boolean loop) {
        this.loop = loop;
    }
}
