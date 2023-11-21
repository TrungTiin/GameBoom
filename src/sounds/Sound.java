package sounds;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Sound {
    private static final List<Clip> allClips = new ArrayList<>();

    public static Clip getSound(URL url){
        try {
            AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(url);
            Clip clip= AudioSystem.getClip();
            clip.open(audioInputStream);
            allClips.add(clip);
            return  clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setVolume(Clip clip, float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public static void setMasterVolume(float volume) {
        for (Clip clip : allClips) {
            setVolume(clip, volume);
        }
    }
}