import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class MyUtil {

    public static int random(int a, int b) {
        int range = b - a + 1;
        return (int) (Math.random() * range) + a;
    }

    public static float f(int i) {
        return (float) (Math.pow(1.059463094359, i) * Main.standardPitch);
    }

    public static ArrayList<Float> generateAttack(int length) {
        float val = 0.0f;
        ArrayList<Float> attack = new ArrayList<Float>();
        for (int i = 0; i < length; val += 0.001f, ++i)
            attack.add(Math.min(1.0f, val));
        return attack;
    }

    public static ArrayList<Float> generateRelease(int length) {
        ArrayList<Float> release = generateAttack(length);
        int i = 0;
        for (; i < 4 * length / 5; ++i) release.set(i, Math.min(0.85f, release.get(i)));
        float val = 0.0f;
        for (; i < length; ++i, val += 0.001f) release.set(i, Math.min(0.85f + val, release.get(i)));
        Collections.reverse(release);
        return release;
    }
    /* frequency x 2pi in one second means frequency (number of wavelengths in one second)
        in other words frequency x 2pi divided by Sample Rate numbers of values in one second is
         ( Hz * 2pi / SampleRate)
    */

    public static ArrayList<Float> generatePulse(float hz, float duration) {
        float step = ((2.0f * (float) Math.acos(-1.0f) * hz) / Main.SampleRate);
        ArrayList<Float> pulses = new ArrayList<Float>();
        ArrayList<Float> attack = generateAttack((int) (Main.SampleRate * duration + 1));
        ArrayList<Float> release = generateRelease((int) (Main.SampleRate * duration + 1));
        for (int i = 0; i < (int) Main.SampleRate * duration + 1; ++i)
            pulses.add((float) (Main.volume * attack.get(i) * release.get(i) * Math.sin(i * step)));
        return pulses;
    }

    public static byte[] fromBufferToAudioBytes(ArrayList<Float> buffer) {
        byte[] audioBytes = new byte[2 * buffer.size()];
        for (int i = 0, n = buffer.size(); i < n; i++) {
            buffer.set(i, buffer.get(i) * 32767);
            audioBytes[i * 2] = buffer.get(i).byteValue();
            audioBytes[i * 2 + 1] = (byte) (buffer.get(i).intValue() >> 8);
        }
        return audioBytes;
    }

    public static void convertFloatsToWav(Wave myWave, String WaveFileName) {
        float duration = myWave.getTotalDuration();
        try (InputStream in = new ByteArrayInputStream(fromBufferToAudioBytes(myWave.getFloats()))) {
            AudioFormat myAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, Main.SampleRate, 16, 1, 4, Main.SampleRate, false);
            AudioInputStream myAudioStream = new AudioInputStream(in, myAudioFormat, (int) (duration * Main.SampleRate));
            AudioSystem.write(myAudioStream, AudioFileFormat.Type.WAVE, new File(WaveFileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
