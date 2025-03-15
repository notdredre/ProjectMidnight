package game.sound;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip clip;
    private boolean loop;

    public Sound(String path, boolean loop, float volume) {
        File target = new File(path);
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(target);
            clip = AudioSystem.getClip();
            clip.open(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.loop = loop;
        setVolume(volume);
    }

    public Sound(String path, float volume) {
        this(path, false, volume);
    }

    public Sound(String path) {
        this(path, false, 1);
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

    public boolean isPlaying() {
        return clip.getLongFramePosition() != 0;
    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gain.getMaximum() - gain.getMinimum();
            float adjust = (range * volume) + gain.getMinimum();
            gain.setValue(adjust);
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
