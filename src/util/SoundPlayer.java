package util;

import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer {
    public static void play(String path) {
        try {
            File soundFile = new File(path);
            if (!soundFile.exists()) {
                System.out.println("❌ File not found: " + soundFile.getAbsolutePath());
                return;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("❌ Unsupported Audio Format: " + path);
            System.out.println("👉 Solution: Convert this WAV to '16-bit PCM' format using an online converter.");
        } catch (Exception e) {
            System.out.println("❌ Sound Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}